package com.sdi.business;

import java.util.List;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.model.Trip;
import com.sdi.model.User;

public interface TripService {

	List<Trip> getViajes() throws Exception;
	
	List<Trip> getViajesConPlazasYSinCerrar() throws Exception;

	Trip findById(Long id) throws EntityNotFoundException;

	void saveTrip(Trip trip) throws EntityAlreadyExistsException;

	void updateTrip(Trip trip) throws EntityNotFoundException;

	void deleteTrip(Long id) throws EntityNotFoundException;
	
	User findPromotor(Long id);
}
