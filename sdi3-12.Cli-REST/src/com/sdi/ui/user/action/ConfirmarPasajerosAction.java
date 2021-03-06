package com.sdi.ui.user.action;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.modelo.Trip;
import com.sdi.modelo.User;
import com.sdi.util.Contexto;

public class ConfirmarPasajerosAction implements Action {

	@Override
	public void execute() throws Exception {
		GenericType<List<Trip>> listv = new GenericType<List<Trip>>() {
		};
		GenericType<List<User>> listp = new GenericType<List<User>>() {
		};

		List<Trip> viajes = ClientBuilder.newClient()
				.target(Contexto.REST_SERVICE_URL + "viajes/")
				.path(Contexto.usuario.getId().toString()).request().get()
				.readEntity(listv);

		if (!viajes.isEmpty()) {
			for (int i = 0; i < viajes.size(); i++) {
				Console.println(i + " " + viajes.get(i));
			}

			int iv = Console
					.readInt("Indica el viaje para el que quieres ver las peticiones");
			if (iv >= 0 && iv < viajes.size()) {
				boolean existeViaje = false;
				for (int i = 0; i < viajes.size(); i++) {
					if (viajes.get(i).getId().equals(viajes.get(iv).getId()))
						existeViaje = true;
				}
				if (existeViaje) {
					List<User> users = ClientBuilder.newClient()
							.target(Contexto.REST_SERVICE_URL + "pendientes/")
							.path(viajes.get(iv).getId().toString()).request()
							.get().readEntity(listp);
					if (!users.isEmpty()) {
						for (int i = 0; i < users.size(); i++) {
							Console.println(i + " " + users.get(i));
						}

						int iu = Console
								.readInt("Indica el usuario que quieres aceptar");

						if (iu >= 0 && iu < users.size()) {
							boolean existeUsuario = false;
							for (int i = 0; i < users.size(); i++) {
								if (users.get(i).getId()
										.equals(users.get(iu).getId()))
									existeUsuario = true;
							}
							if (existeUsuario) {
								List<Long> datos = new ArrayList<>();
								datos.add(viajes.get(iv).getId());
								datos.add(users.get(iu).getId());

								ClientBuilder
										.newClient()
										.target(Contexto.REST_SERVICE_URL)
										.request()
										.put(Entity.entity(datos,
												MediaType.APPLICATION_JSON));

								Console.println("Usuario aceptado");
							} else {
								System.out.println("El usuario " + iu
										+ " no esta en este viaje");
							}
						} else {
							System.out
									.println("Usuario seleccionado fuera de rango");
						}
					} else {
						System.out
								.println("No hay usuarios pendientes para este viaje");
					}
				} else {
					System.out.println("El viaje " + iv + " no te pertenece");
				}
			} else {
				System.out.println("Viaje seleccionado fuera de rango");
			}
		} else {
			System.out.println("No tienes viajes");
		}
	}

}
