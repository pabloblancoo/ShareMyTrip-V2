package com.sdi.business.impl.classes.Trip;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

public class ListarViajes {

	public List<Trip> run(){
		return Factories.persistence.newTripDao().findAll();
	}
}
