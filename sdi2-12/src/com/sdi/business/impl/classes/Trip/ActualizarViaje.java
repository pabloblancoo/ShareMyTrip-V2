package com.sdi.business.impl.classes.Trip;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

public class ActualizarViaje {

	public void run(Trip trip){

		Factories.persistence.newTripDao().update(trip);
	}
}
