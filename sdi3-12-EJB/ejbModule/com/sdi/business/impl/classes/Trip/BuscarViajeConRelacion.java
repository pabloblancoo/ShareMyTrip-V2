package com.sdi.business.impl.classes.Trip;

import java.util.List;
import java.util.ResourceBundle;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;
import com.sdi.util.MisViajesConEstado;

public class BuscarViajeConRelacion {
	
	
	/**
	 * Metodo que cargar los viajes con relacion(excepto promotor) al usuario
	 * que se pasa como parametro
	 * 
	 * @param misViajes
	 *            lista a la que se quieren añadir los viajes
	 * @param idUsuario
	 *            id del usuario del que se buscan los viajes
	 */
	public void run(List<MisViajesConEstado> misViajes,
			Long idUsuario, ResourceBundle msgs){
		
		List<Application> peticiones = Factories.persistence
				.newApplicationDao().findByUserId(idUsuario);
		SeatDao sd = Factories.persistence.newSeatDao();
		TripDao td = Factories.persistence.newTripDao();

		for (Application peticion : peticiones) {
			Seat plaza = sd.findByUserAndTrip(idUsuario, peticion.getTripId());
			Trip viaje = td.findById(peticion.getTripId());
			if (plaza != null) {
				if (plaza.getStatus().equals(SeatStatus.ACCEPTED)) {
					misViajes.add(new MisViajesConEstado(viaje, msgs
							.getString("ownTripAccepted"), msgs));
				} else {
					misViajes.add(new MisViajesConEstado(viaje, msgs
							.getString("ownTripExcluded"), msgs));
				}
			} else if (plaza == null && viaje.getAvailablePax() > 0) {
				misViajes.add(new MisViajesConEstado(viaje, msgs
						.getString("ownTripPending"), msgs));
			} else {
				misViajes.add(new MisViajesConEstado(viaje, msgs
						.getString("ownTripNoSeat"), msgs));
			}
		}
		
	}

}
