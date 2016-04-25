package com.sdi.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.sdi.business.TripService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.util.MisViajesConEstado;

@ManagedBean(name = "misViajes")
public class BeanMisViajes {

	List<MisViajesConEstado> viajes;

	private ResourceBundle msgs = FacesContext.getCurrentInstance()
			.getApplication()
			.getResourceBundle(FacesContext.getCurrentInstance(), "msgs");

	/**
	 * Carga los viajes del usuario con sesion iniciada si hay cambios
	 * 
	 * @return List<MisViajesConEstado>
	 */
	public List<MisViajesConEstado> getViajes() {
		List<MisViajesConEstado> viajesDB = new ArrayList<>();
		BeanUsuario usuario = ((BeanSettings) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("settings"))
				.getUsuario();

		if (usuario != null) {
			TripService ts = Factories.services.createTripService();
			ts.buscarViajesPromotor(viajesDB, usuario.getId(), msgs);
			ts.buscarViajesConRelacion(viajesDB, usuario.getId(), msgs);
		}

		if (viajes == null || viajesDB.size() != viajes.size()) {
			viajes = viajesDB;
			System.out.println("Viajes [" + viajes.size() + "] del usuario "
					+ usuario.getLogin());
		}

		return viajes;
	}

	public void setViajes(List<MisViajesConEstado> viajes) {
		this.viajes = viajes;
	}


	/**
	 * Metodo que se encarga de cancelar la peticion(y plaza si ha sido
	 * admintido) o del viaje del usuario con sesion iniciada y el viaje
	 * seleccionado
	 */
	public String cancelar(MisViajesConEstado trip) {

		cancelarPrivado(trip);

		return null;
	}

	/**
	 * Metodo que apartir de la lista de los indices de los viajes seleccionados
	 * obtiene el viaje y se lo pasa al metodo cancelar para que lo cancele
	 */
	public String cancelacionMultiple() {
		int cancelados = 0;

		for (MisViajesConEstado viaje : viajes) {
			if (viaje.isSeleccionado()) {
				cancelados++;
				cancelarPrivado(viaje);
			}
		}
		System.out.println("Cancelados " + cancelados + " viajes/plazas.");
		return null;
	}

	/**
	 * Metodo privado que cancela el viaje que recibe como parametro
	 * 
	 * @param trip
	 */
	private void cancelarPrivado(MisViajesConEstado trip) {
		Long idUsuario = ((BeanSettings) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("settings"))
				.getUsuario().getId();

		if (!trip.getViaje().getPromoterId().equals(idUsuario)) {

			Trip viaje = Factories.services.createApplicationService().cancelarParticipacion(trip, idUsuario).getViaje();
			trip.setViaje(viaje);
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msgs
							.getString("ownTripCancelSeatSuccessful")));

			System.out.println("Plaza/peticion cancelada");

		} else {

			Trip viaje = trip.getViaje();
			viaje.setStatus(TripStatus.CANCELLED);
			
			Factories.services.createTripService().cancelarViaje(viaje);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msgs
							.getString("ownTripCancelTripSuccessful")));

			System.out.println("Viaje cancelado con exito");
		}
	}

	/**
	 * Metodo que se encarga de enviar los datos del viaje a editar al beanViaje
	 * 
	 * @param trip
	 * @return
	 */
	public String editar(MisViajesConEstado trip) {

		BeanViaje viaje = (BeanViaje) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("viaje"));
		if (viaje == null) {
			viaje = new BeanViaje();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put(new String("viaje"), viaje);
		}
		viaje.cargarViaje(trip.getViaje());

		return "editar";
	}

}
