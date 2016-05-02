package com.sdi.ui.admin.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.ws.EjbTripsServiceService;
import com.sdi.ws.EjbUserServiceService;
import com.sdi.ws.Trip;
import com.sdi.ws.TripService;
import com.sdi.ws.User;

public class ListarUsuariosAction implements Action{

	@Override
	public void execute() throws Exception {
		
		List<User>usuarios  =  new EjbUserServiceService().getUserServicePort().listar();
		TripService ts = new EjbTripsServiceService().getTripServicePort();
		
		for(User user : usuarios){
			List<Trip> promotor = ts.buscarViajesPorPromotor(user.getId());
			List<Trip> participado = ts.buscarViajesParticipa(user.getId());
			
			Console.printf("%s %s (a.k.a %s) con email %s ha promovido %s viaje/s y ha participado en %s viaje/s\n",
					user.getName(), user.getSurname(), user.getLogin(), user.getEmail(),
					promotor.size() + "", participado.size() + "");
		}
		
	}

}
