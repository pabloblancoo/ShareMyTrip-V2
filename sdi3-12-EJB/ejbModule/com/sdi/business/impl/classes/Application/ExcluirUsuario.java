package com.sdi.business.impl.classes.Application;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.persistence.PersistenceFactory;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;

public class ExcluirUsuario {

	public void run(User user, Trip viaje) {
		PersistenceFactory p = Factories.persistence;
		SeatDao sd = p.newSeatDao();
		TripDao td = p.newTripDao();
		
		Seat seat = sd.findByUserAndTrip(user.getId(), viaje.getId());
		seat.setStatus(SeatStatus.EXCLUDED);
		sd.update(seat);
		
		viaje.setAvailablePax(viaje.getAvailablePax() + 1);
		td.update(viaje);		
	}

}
