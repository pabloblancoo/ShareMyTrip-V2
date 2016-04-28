package com.sdi.ui.user.action;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import com.sdi.modelo.Trip;
import com.sdi.ui.user.MainMenu;

import alb.util.console.Console;
import alb.util.menu.Action;

public class ConectarseAction implements Action{

	@Override
	public void execute() throws Exception {
		GenericType<List<Trip>> listm = new GenericType<List<Trip>>() {};

		List<Trip> res = ClientBuilder.newClient()
				.target( MainMenu.REST_SERVICE_URL +"viajes-participa/" )
				.path( MainMenu.usuario.getId().toString() )
				.request()
				.get()
				.readEntity( listm );

		for(Trip viaje : res){
			Console.println(viaje);
		}

	}



}
