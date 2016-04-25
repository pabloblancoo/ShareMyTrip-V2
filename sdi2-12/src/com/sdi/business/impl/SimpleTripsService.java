package com.sdi.business.impl;

import java.util.List;
import java.util.ResourceBundle;

import com.sdi.business.TripService;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.business.impl.classes.Trip.BuscarPromotor;
import com.sdi.business.impl.classes.Trip.BuscarViajeConRelacion;
import com.sdi.business.impl.classes.Trip.BuscarViajesPromotor;
import com.sdi.business.impl.classes.Trip.CancelarViaje;
import com.sdi.business.impl.classes.Trip.ViajeBuscar;
import com.sdi.business.impl.classes.Trip.ViajesConPlazasYSinCerrar;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.util.MisViajesConEstado;

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

	@Override
	public List<Trip> getViajesConPlazasYSinCerrar() throws Exception {
		return new ViajesConPlazasYSinCerrar().buscar();
	}

	@Override
	public User findPromotor(Long id) {
		return new BuscarPromotor().run(id);
	}

	@Override
	public void buscarViajesPromotor(List<MisViajesConEstado> misViajes,
			Long idUsuario, ResourceBundle msgs) {
		new BuscarViajesPromotor().run(misViajes, idUsuario, msgs);	
	}

	@Override
	public void buscarViajesConRelacion(List<MisViajesConEstado> misViajes,
			Long idUsuario, ResourceBundle msgs) {
		new BuscarViajeConRelacion().run(misViajes, idUsuario, msgs);
	}

	@Override
	public void cancelarViaje(Trip viaje) {
		new CancelarViaje().run(viaje);
	}

}
