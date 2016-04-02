package com.sdi.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;
import com.sdi.util.MisViajesConEstado;

@ManagedBean(name="misViajes")
public class BeanMisViajes {

	List<MisViajesConEstado> viajes;

	private ResourceBundle msgs = FacesContext.getCurrentInstance()
			.getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs");

	/**
	 * Carga los viajes del usuario con sesion iniciada si hay cambios
	 * @return List<MisViajesConEstado>
	 */
	public List<MisViajesConEstado> getViajes() {		
		List<MisViajesConEstado> viajesDB = new ArrayList<>();
		BeanUsuario usuario = ((BeanSettings) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("settings")).getUsuario();

		if(usuario != null){
			cargarViajesPromotor(viajesDB, usuario.getId());
			cargarViajes(viajesDB, usuario.getId());
		}
		
		if(viajes == null || viajesDB.size() != viajes.size()){
			viajes = viajesDB;
			System.out.println("Viajes [" + viajes.size() + "] del usuario " + usuario.getLogin());
		}

		return viajes;
	}

	public void setViajes(List<MisViajesConEstado> viajes) {
		this.viajes = viajes;
	}

	/**
	 * Metodo que cargar los viajes en los que es promotor el usuario pasado como parametro
	 * @param misViajes lista a la que se quieren añadir los viajes
	 * @param idUsuario id del usuario del que se buscan los viajes
	 */
	private void cargarViajesPromotor(List<MisViajesConEstado> misViajes, Long idUsuario) {
		List<Trip> viajes = Factories.persistence.newTripDao().findByPromoterId(idUsuario);
		for(Trip viaje : viajes){
			misViajes.add(new MisViajesConEstado(viaje, msgs.getString("tripPromoter")));
		}
	}

	/**
	 * Metodo que cargar los viajes con relacion(excepto promotor) al usuario que se pasa como parametro
	 * @param misViajes lista a la que se quieren añadir los viajes
	 * @param idUsuario id del usuario del que se buscan los viajes
	 */
	private void cargarViajes(List<MisViajesConEstado> misViajes, Long idUsuario){

		List<Application> peticiones = Factories.persistence.newApplicationDao().findByUserId(idUsuario);
		SeatDao sd = Factories.persistence.newSeatDao();
		TripDao td = Factories.persistence.newTripDao();

		for(Application peticion : peticiones){
			Seat plaza = sd.findByUserAndTrip(idUsuario, peticion.getTripId());
			Trip viaje = td.findById(peticion.getTripId());
			if(plaza != null){
				if(plaza.getStatus().equals(SeatStatus.ACCEPTED)){
					misViajes.add(new MisViajesConEstado(viaje, msgs.getString("ownTripAccepted")));
				}
				else{
					misViajes.add(new MisViajesConEstado(viaje, msgs.getString("ownTripExcluded")));
				}
			}
			else if(plaza == null && viaje.getAvailablePax() > 0){
				misViajes.add(new MisViajesConEstado(viaje, msgs.getString("ownTripPending")));
			}
			else{
				misViajes.add(new MisViajesConEstado(viaje, msgs.getString("ownTripNoSeat")));
			}
		}
	}

	/**
	 * Metodo que se encarga de cancelar la peticion(y plaza si ha sido admintido)
	 * o del viaje del usuario con sesion iniciada y el viaje seleccionado
	 */
	public String cancelar(MisViajesConEstado trip){

		cancelarPrivado(trip);

		return null;
	}
	
	/**
	 * Metodo que apartir de la lista de los indices de los viajes seleccionados
	 * obtiene el viaje y se lo pasa al metodo cancelar para que lo cancele
	 */
	public String cancelacionMultiple(){
		int cancelados = 0;
		
		for(MisViajesConEstado viaje: viajes){
			if(viaje.isSeleccionado()){
				cancelados++;
				cancelarPrivado(viaje);
			}
		}
		System.out.println("Cancelados " + cancelados + " viajes/plazas.");
		return null;
	}
	
	/**
	 * Metodo privado que cancela el viaje que recibe como parametro
	 * @param trip
	 */
	private void cancelarPrivado(MisViajesConEstado trip){
		Long idUsuario = ((BeanSettings) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("settings")).getUsuario().getId();

		if(!trip.getViaje().getPromoterId().equals(idUsuario)){

			Long idTrip = trip.getViaje().getId();

			SeatDao sd = Factories.persistence.newSeatDao();
			Seat plaza = sd.findByUserAndTrip(idUsuario, idTrip);

			sd.delete(new Long[]{idUsuario, idTrip});
			Factories.persistence.newApplicationDao().delete(new Long[]{idUsuario, idTrip});

			if(plaza != null && plaza.getStatus().equals(SeatStatus.ACCEPTED)){
				TripDao td = Factories.persistence.newTripDao();

				Trip viaje = td.findById(idTrip);
				viaje.setAvailablePax(viaje.getAvailablePax() + 1);

				td.update(viaje);
				System.out.println("Plaza liberada en el viaje[id:" + idTrip + "]");

			}

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msgs.getString("ownTripCancelSeatSuccessful")));

			System.out.println("Plaza/peticion cancelada");

		}
		else{
			
			Trip viaje = trip.getViaje();
			viaje.setStatus(TripStatus.CANCELLED);
			
			Factories.persistence.newTripDao().update(viaje);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msgs.getString("ownTripCancelTripSuccessful")));

			System.out.println("Viaje cancelado con exito");
		}
	}
	
	/**
	 * Metodo que se encarga de enviar los datos del viaje a editar al beanViaje
	 * @param trip
	 * @return
	 */
	public String editar(MisViajesConEstado trip) {

		BeanViaje viaje = (BeanViaje) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("viaje"));
		if(viaje == null){
			viaje = new BeanViaje();
			FacesContext.getCurrentInstance()
			.getExternalContext().getSessionMap().put(new String("viaje"), viaje);
		}
		viaje.cargarViaje(trip.getViaje());

		return "editar";
	}
	
}
