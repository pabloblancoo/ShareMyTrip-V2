package com.sdi.presentation.rest;

import java.util.List;

import com.sdi.model.Trip;
import com.sdi.model.User;

public interface ServiceRest {

	public List<Trip> listarViajesUsuario(long idUser);
	
	public List<User> listaUsuariosPendientes(long idViaje);
	
	public void aceptarViajero(long idViaje, long idUsuario);
	
	
}
