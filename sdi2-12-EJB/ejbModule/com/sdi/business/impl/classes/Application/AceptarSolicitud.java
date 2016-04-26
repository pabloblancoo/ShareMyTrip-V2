package com.sdi.business.impl.classes.Application;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.persistence.PersistenceFactory;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;
import com.sdi.util.Viajero;

public class AceptarSolicitud {
	
	public Viajero run(Viajero viajero, Trip viaje){
		
		PersistenceFactory p = Factories.persistence;
		SeatDao sd = p.newSeatDao();
		TripDao td = p.newTripDao();

		if (viajero.getSeat() == null) {
			Seat seat = new Seat();
			seat.setComment("");
			seat.setStatus(SeatStatus.ACCEPTED);
			seat.setTripId(viaje.getId());
			seat.setUserId(viajero.getUser().getId());

			sd.save(seat);

			viajero.setSeat(seat);
		} else {
			viajero.getSeat().setStatus(SeatStatus.ACCEPTED);

			sd.update(viajero.getSeat());
		}
		
		viaje.setAvailablePax(viaje.getAvailablePax() - 1);
		td.update(viaje);
				
		return viajero;
		
	}

}
