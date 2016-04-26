package com.sdi.business.impl.classes.Trip;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

public class RegistrarViaje {

	public void run(Trip trip){
		Factories.persistence.newTripDao().save(trip);
	}
}
