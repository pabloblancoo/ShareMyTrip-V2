package com.sdi.ui.user.action;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.modelo.Trip;
import com.sdi.util.Contexto;

public class ConectarseAction implements Action{

	@Override
	public void execute() throws Exception {
		GenericType<List<Trip>> listm = new GenericType<List<Trip>>() {};

		List<Trip> res = ClientBuilder.newClient()
				.target( Contexto.REST_SERVICE_URL +"viajes-participa/" )
				.path( Contexto.usuario.getId().toString() )
				.request()
				.get()
				.readEntity( listm );

		for(Trip viaje : res){
			Console.println(viaje);
		}

	}



}
