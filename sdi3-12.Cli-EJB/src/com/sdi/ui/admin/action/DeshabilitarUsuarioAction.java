package com.sdi.ui.admin.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.business.ApplicationService;
import com.sdi.business.TripService;
import com.sdi.business.UserService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.User;
import com.sdi.model.UserStatus;

public class DeshabilitarUsuarioAction implements Action {

	@Override
	public void execute() throws Exception {
		UserService us =  new RemoteEjbServicesLocator().getUserService();
		TripService ts = new RemoteEjbServicesLocator().getTripService();
		ApplicationService as= new RemoteEjbServicesLocator().getApplicationService();
		
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
				as.exclude(usuarios.get(indice),  viaje);
			}
		}
		
		Console.printf("Usuario %s deshabilitado correctamente.\n", usuarios.get(indice).getName() );

	}

}
