package com.sdi.rest.impl;

import java.util.List;

import com.sdi.business.exception.BusinessException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.rest.ServiceRest;

public class ServiceRestImpl implements ServiceRest {

	
	@Override
	public List<Trip> listarViajesUsuario(long idUser) {
		return Factories.services.getTripService().buscarViajesPromotor(idUser);
	}

	@Override
	public void aceptarViajero(List<Long> datos) {
		Factories.services.getApplicationService().acceptarUsuario(datos.get(0), datos.get(1));

	}

	@Override
	public List<User> listaUsuariosPendientes(long idViaje) {
		return Factories.services.getUserService().getUsuariosViaje(idViaje);
	}

	@Override
	public User iniciarSesion(User datos) {
		try{
			return Factories.services.getUserService().iniciarSesion(datos.getLogin(), datos.getPassword());
		} catch(BusinessException e){
			return null;
		}
		
	}

}
