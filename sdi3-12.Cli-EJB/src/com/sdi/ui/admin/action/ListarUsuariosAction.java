package com.sdi.ui.admin.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.business.TripService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.Trip;
import com.sdi.model.User;

public class ListarUsuariosAction implements Action{

	@Override
	public void execute() throws Exception {
		
		List<User>usuarios  =  new RemoteEjbServicesLocator().getUserService().listar();
		TripService ts = new RemoteEjbServicesLocator().getTripService();
		
		for(User user : usuarios){
			List<Trip> promotor = ts.buscarViajesPromotor(user.getId());
			List<Trip> participado = ts.buscarViajesParticipa(user.getId());
			
			Console.printf("%s %s (a.k.a %s) con email %s ha promovido %s viaje/s y ha participado en %s viaje/s\n",
					user.getName(), user.getSurname(), user.getLogin(), user.getEmail(),
					promotor.size() + "", participado.size() + "");
		}
		
	}

}
