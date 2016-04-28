package com.sdi.business;

import java.util.List;
import java.util.ResourceBundle;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.util.MisViajesConEstado;

public interface TripService {

	List<Trip> getViajes() throws Exception;
	
	List<Trip> getViajesConPlazasYSinCerrar() throws Exception;

	Trip findById(Long id) throws EntityNotFoundException;

	void saveTrip(Trip trip) throws EntityAlreadyExistsException;

	void updateTrip(Trip trip) throws EntityNotFoundException;

	void deleteTrip(Long id) throws EntityNotFoundException;
	
	User findPromotor(Long id);
	
	List<Trip> buscarViajesPromotor(Long idUser);
	List<Trip> buscarViajesParticipa(Long idUser);
	
	void buscarViajesPromotor(List<MisViajesConEstado> misViajes, Long idUsuario, ResourceBundle msgs); 
	void buscarViajesConRelacion(List<MisViajesConEstado> misViajes, Long idUsuario, ResourceBundle msgs); 

	void cancelarViaje(Trip viaje);

	List<Trip> getViajesOrdenadorUltimoMes();
	List<Trip> getViajesPorUsuarioPromotorOAceptado(Long idUser);

}
