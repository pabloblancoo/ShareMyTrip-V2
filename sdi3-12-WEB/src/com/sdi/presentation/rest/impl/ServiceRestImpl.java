package com.sdi.presentation.rest.impl;

import java.util.ArrayList;
import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.presentation.rest.ServiceRest;

public class ServiceRestImpl implements ServiceRest {

	@Override
	public List<Trip> listarViajesUsuario(long idUser) {
		return Factories.services.getTripService().buscarViajesPromotor(idUser);
	}

	@Override
	public void aceptarViajero(long idViaje, long idUsuario) {
//		Factories.services.getApplicationService().accept(viajero, viaje)
		
	}

	@Override
	public List<User> listaUsuariosPendientes(long idViaje) {
		List<User> users = new ArrayList<>();
		
		
		
		return users;
	}

}
