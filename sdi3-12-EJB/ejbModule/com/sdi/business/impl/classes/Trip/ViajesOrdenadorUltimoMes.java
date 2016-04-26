package com.sdi.business.impl.classes.Trip;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

public class ViajesOrdenadorUltimoMes {

	public List<Trip> run(){
		return Factories.persistence.newTripDao().findLastMonthOrderedTrips();
	}
}
