package com.sdi.ui.admin.action;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.modelo.Trip;
import com.sdi.ui.admin.MainMenu;

public class ListarViajesAction implements Action{

	@Override
	public void execute() throws Exception {
		
		GenericType<List<Trip>> listm = new GenericType<List<Trip>>() {};
		
		List<Trip> res = ClientBuilder.newClient()
			 .target( MainMenu.REST_SERVICE_URL +"viajes/" )
			 .path( MainMenu.usuario.getId().toString() )
			.request()
			.get()
			.readEntity( listm );
		
		for(Trip viaje : res){
			Console.println(viaje);
		}
		
	}

}
