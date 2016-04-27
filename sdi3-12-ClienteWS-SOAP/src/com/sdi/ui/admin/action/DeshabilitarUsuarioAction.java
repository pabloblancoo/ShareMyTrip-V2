package com.sdi.ui.admin.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.ws.ApplicationService;
import com.sdi.ws.EjbApplicationServiceService;
import com.sdi.ws.EjbTripsServiceService;
import com.sdi.ws.EjbUserServiceService;
import com.sdi.ws.Trip;
import com.sdi.ws.TripService;
import com.sdi.ws.TripStatus;
import com.sdi.ws.User;
import com.sdi.ws.UserService;
import com.sdi.ws.UserStatus;

public class DeshabilitarUsuarioAction implements Action {

	@Override
	public void execute() throws Exception {
		UserService us = new EjbUserServiceService().getUserServicePort();
		TripService ts = new EjbTripsServiceService().getTripServicePort();
		ApplicationService as= new EjbApplicationServiceService().getApplicationServicePort();
		
		List<User>usuarios  =  us.listar();
		
		Console.println("num____nombre");
		for(int i = 0; i < usuarios.size(); i++){
			Console.printf("%d\tUsuario %s\n", i, usuarios.get(i).getName());
		}
		
		int indice = Console.readInt("num. del usuario a deshabilitar.");
		usuarios.get(indice).setStatus(UserStatus.CANCELLED);
		us.actualizar(usuarios.get(indice));
		
		List<Trip> viajes = ts.buscarViajesParticipa(usuarios.get(indice).getId());
		for(Trip viaje : viajes){
			if(viaje.getStatus().equals(TripStatus.OPEN)){
				as.excludeUser(usuarios.get(indice),  viaje);
			}
		}
		
		Console.printf("Usuario %s deshabilitado correctamente.\n", usuarios.get(indice).getName() );

	}

}
