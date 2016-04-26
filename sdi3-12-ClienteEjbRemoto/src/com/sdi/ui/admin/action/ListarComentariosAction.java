package com.sdi.ui.admin.action;

import java.util.List;

import com.sdi.business.RatingService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Rating;

import alb.util.menu.Action;

public class ListarComentariosAction implements Action{

	@Override
	public void execute() throws Exception {
		RatingService r = new RemoteEjbServicesLocator().getRatingService();
		List<Rating> ratings = r.getRatings();
		for (Rating rating : ratings) {
			System.out.println(rating.getComment() + " " + rating.getId());
		}
		
	}
	
	

}
