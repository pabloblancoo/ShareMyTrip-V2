package com.sdi.business.impl.classes.Trip;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

public class CancelarViaje {
	
	public void run(Trip viaje){
		Factories.persistence.newTripDao().update(viaje);
	}

}
