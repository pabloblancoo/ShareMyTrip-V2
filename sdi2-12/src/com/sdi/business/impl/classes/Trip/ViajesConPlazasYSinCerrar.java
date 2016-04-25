package com.sdi.business.impl.classes.Trip;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

public class ViajesConPlazasYSinCerrar {
	
	public List<Trip> buscar() {

		return Factories.persistence.newTripDao()
				.findAllOpenAndPaxAvailables();
	}

}
