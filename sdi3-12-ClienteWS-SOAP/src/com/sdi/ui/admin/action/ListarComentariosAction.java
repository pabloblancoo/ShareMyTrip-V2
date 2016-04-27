package com.sdi.ui.admin.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.ui.util.Printer;
import com.sdi.ws.EjbRatingServiceService;
import com.sdi.ws.EjbTripsServiceService;
import com.sdi.ws.Rating;
import com.sdi.ws.RatingService;
import com.sdi.ws.Trip;
import com.sdi.ws.TripService;

public class ListarComentariosAction implements Action {

	@Override
	public void execute() throws Exception {
		RatingService ratingService = new EjbRatingServiceService().getRatingServicePort();
		TripService tripService = new EjbTripsServiceService().getTripServicePort();
		
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
