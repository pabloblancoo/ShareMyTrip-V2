package com.sdi.ui.user.action;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.modelo.Trip;
import com.sdi.util.Contexto;

public class ConectarseAction implements Action {

	@Override
	public void execute() throws Exception {

		GenericType<List<Trip>> listm = new GenericType<List<Trip>>() {
		};

		List<Trip> res = ClientBuilder.newClient()
				.target(Contexto.REST_SERVICE_URL + "viajes-participa/")
				.path(Contexto.usuario.getId().toString()).request().get()
				.readEntity(listm);

		Console.println();
		for (Trip viaje : res) {
			Console.println(viaje);
		}
		Console.println();
		long idViaje = Console.readLong("Seleccione id del viaje");
		boolean existeViaje = false;
		for (Trip viaje : res) {
			if (idViaje == viaje.getId()) {
				Contexto.trip = viaje;
				existeViaje = true;
			}
		}
		if (!existeViaje) {
			Console.println("Viaje no existe");
		}

//		new LeerMensajes().leerMensajes();
	}

}
