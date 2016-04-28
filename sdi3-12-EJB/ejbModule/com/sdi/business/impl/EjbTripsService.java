package com.sdi.business.impl;

import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.business.impl.EjbTrips.LocalTripsService;
import com.sdi.business.impl.EjbTrips.RemoteTripsService;
import com.sdi.business.impl.classes.Trip.ActualizarViaje;
import com.sdi.business.impl.classes.Trip.BuscarPromotor;
import com.sdi.business.impl.classes.Trip.BuscarViajeConRelacion;
import com.sdi.business.impl.classes.Trip.BuscarViajesPorPromotor;
import com.sdi.business.impl.classes.Trip.BuscarViajesPromotor;
import com.sdi.business.impl.classes.Trip.CancelarViaje;
import com.sdi.business.impl.classes.Trip.ListarViajes;
import com.sdi.business.impl.classes.Trip.ListarViajesParticipa;
import com.sdi.business.impl.classes.Trip.ListarViajesUsuarioPromotorOAceptado;
import com.sdi.business.impl.classes.Trip.RegistrarViaje;
import com.sdi.business.impl.classes.Trip.ViajeBuscar;
import com.sdi.business.impl.classes.Trip.ViajesConPlazasYSinCerrar;
import com.sdi.business.impl.classes.Trip.ViajesOrdenadorUltimoMes;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.util.MisViajesConEstado;

@Stateless
@WebService(name="TripService")
public class EjbTripsService implements LocalTripsService, RemoteTripsService {

	@Override
	public List<Trip> getViajes() throws Exception {
		return new ListarViajes().run();
	}

	@Override
	public Trip findById(Long id) throws EntityNotFoundException {
		return new ViajeBuscar().buscar(id);
	}

	@Override
	public void saveTrip(Trip trip) throws EntityAlreadyExistsException {
		new RegistrarViaje().run(trip);
	}

	@Override
	public void updateTrip(Trip trip) throws EntityNotFoundException {
		new ActualizarViaje().run(trip);
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

	@Override
	public List<Trip> getViajesOrdenadorUltimoMes() {
		return new ViajesOrdenadorUltimoMes().run();
	}
	
	@WebMethod(operationName="buscarViajesPorPromotor")
	public List<Trip> buscarViajesPromotor(Long idUser) {
		return new BuscarViajesPorPromotor().run(idUser);
	}

	@Override
	public List<Trip> buscarViajesParticipa(Long idUser) {
		return new ListarViajesParticipa().run(idUser);
	}

	@Override
	public List<Trip> getViajesPorUsuarioPromotorOAceptado(Long idUser) {
		return new ListarViajesUsuarioPromotorOAceptado().run(idUser);
	}

	
	
	

}
