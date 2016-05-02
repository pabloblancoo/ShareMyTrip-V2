package com.sdi.ui.user.action;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.modelo.Trip;
import com.sdi.util.Contexto;

public class ListarViajesAction implements Action {

	@Override
	public void execute() throws Exception {

		GenericType<List<Trip>> listm = new GenericType<List<Trip>>() {
		};

		List<Trip> res = ClientBuilder.newClient()
				.target(Contexto.REST_SERVICE_URL + "viajes/")
				.path(Contexto.usuario.getId().toString()).request().get()
				.readEntity(listm);
		if (!res.isEmpty()) {

			for (Trip viaje : res) {
				Console.println(viaje);
			}
		} else

			System.out.println("No tienes viajes");

	}

}
