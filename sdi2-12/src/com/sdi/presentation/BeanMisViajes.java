package com.sdi.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;
import com.sdi.util.MisViajesConEstado;

@ManagedBean(name="misViajes")
public class BeanMisViajes {

	List<MisViajesConEstado> viajes;
	
	private ResourceBundle msgs = FacesContext.getCurrentInstance()
			.getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs");

	public List<MisViajesConEstado> getViajes() {
		viajes = new ArrayList<>();
		BeanUsuario usuario = ((BeanSettings) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("settings")).getUsuario();
		
		cargarViajesPromotor(viajes, usuario.getId());
		cargarViajes(viajes, usuario.getId());
		
		return viajes;
	}

	public void setViajes(List<MisViajesConEstado> viajes) {
		this.viajes = viajes;
	}

	private void cargarViajesPromotor(List<MisViajesConEstado> misViajes, Long idUsuario) {
		List<Trip> viajes = Factories.persistence.newTripDao().findByPromoterId(idUsuario);
		for(Trip viaje : viajes){
			misViajes.add(new MisViajesConEstado(viaje, msgs.getString("tripPromoter")));
		}
	}

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
}
