package com.sdi.business.impl.classes.Application;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;
import com.sdi.util.MisViajesConEstado;

public class CancelarParticipacion {
	
	public MisViajesConEstado run(MisViajesConEstado trip, Long idUsuario){
		
		Long idTrip = trip.getViaje().getId();

		SeatDao sd = Factories.persistence.newSeatDao();
		Seat plaza = sd.findByUserAndTrip(idUsuario, idTrip);

		sd.delete(new Long[] { idUsuario, idTrip });
		Factories.persistence.newApplicationDao().delete(
				new Long[] { idUsuario, idTrip });

		if (plaza != null && plaza.getStatus().equals(SeatStatus.ACCEPTED)) {
			TripDao td = Factories.persistence.newTripDao();

			Trip viaje = td.findById(idTrip);
			viaje.setAvailablePax(viaje.getAvailablePax() + 1);

			td.update(viaje);
			System.out.println("Plaza liberada en el viaje[id:" + idTrip
					+ "]");

		}
		
		return trip;
		
	}

}
