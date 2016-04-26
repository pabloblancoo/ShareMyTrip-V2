package com.sdi.ui.admin.action;

import java.util.List;

import com.sdi.business.RatingService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.Rating;
import com.sdi.ui.util.Printer;

import alb.util.console.Console;
import alb.util.menu.Action;

public class EliminarComentariosAction implements Action{

	@Override
	public void execute() throws Exception {
		
		RatingService r = new RemoteEjbServicesLocator().getRatingService();
		List<Rating> ratings = r.getRatings();
		if (ratings == null) {
			Console.println("No hay comentarios");
		} else {
			for (Rating rating : ratings) {
				Printer.printRating(rating);
			}
			Console.println("Seleccione el id del comentario que desea borrar");
			long id = Console.readLong();
			if(!r.eliminarRating(id))
				Console.println("No se ha podido eliminar el comentario " + id);
			else{
				Console.println("Se ha eliminado correctamente el comentario "+ id);
			}
		}
		
		
		
	}

	
}
