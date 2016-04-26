package com.sdi.business.impl.classes.Trip;

import java.util.ArrayList;
import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;

public class ListarViajesParticipa {

	public List<Trip> run(Long idUser){
		
		List<Trip> viajes = new ArrayList<>();

		List<Application> peticiones = Factories.persistence
				.newApplicationDao().findByUserId(idUser);
		SeatDao sd = Factories.persistence.newSeatDao();
		TripDao td = Factories.persistence.newTripDao();

		for (Application peticion : peticiones) {
			Seat plaza = sd.findByUserAndTrip(idUser, peticion.getTripId());
			Trip viaje = td.findById(peticion.getTripId());
			if (plaza != null) {
				if (plaza.getStatus().equals(SeatStatus.ACCEPTED)) {
					viajes.add(viaje);
				}
			}

		}
		return viajes;
	}

}
