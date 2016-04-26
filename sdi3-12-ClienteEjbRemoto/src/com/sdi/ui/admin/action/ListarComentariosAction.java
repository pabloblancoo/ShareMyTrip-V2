package com.sdi.ui.admin.action;

import java.util.List;

import com.sdi.business.RatingService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TripService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Rating;
import com.sdi.model.Trip;
import com.sdi.ui.util.Printer;

import alb.util.console.Console;
import alb.util.menu.Action;

public class ListarComentariosAction implements Action {

	@Override
	public void execute() throws Exception {
		ServicesFactory service = new RemoteEjbServicesLocator();
		RatingService ratingService = service.getRatingService();
		TripService tripService = service.getTripService();
		
		List<Trip> trips = tripService.getViajesOrdenadorUltimoMes();
		
		for (Trip trip : trips) {
			List<Rating> ratingsTrip = ratingService.getRatingsByTrip(trip.getId());
			if(ratingsTrip != null){
			for (Rating rating : ratingsTrip) {
				Printer.printComentarioViaje(trip,rating);
			}
			}
			else{
				Console.println("No hay comentario para el viaje " + trip.getId());
			}
		}
		

	}

}
