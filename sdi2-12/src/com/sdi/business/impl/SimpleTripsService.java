package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.TripService;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.business.impl.classes.Trip.ViajeBuscar;
import com.sdi.model.Trip;

public class SimpleTripsService implements TripService {

	@Override
	public List<Trip> getViajes() throws Exception {
		return null;
	}

	@Override
	public Trip findById(Long id) throws EntityNotFoundException {
		return new ViajeBuscar().buscar(id);
	}

	@Override
	public void saveTrip(Trip trip) throws EntityAlreadyExistsException {

	}

	@Override
	public void updateTrip(Trip trip) throws EntityNotFoundException {

	}

	@Override
	public void deleteTrip(Long id) throws EntityNotFoundException {

	}

}
