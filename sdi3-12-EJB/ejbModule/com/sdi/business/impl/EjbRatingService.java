package com.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.RatingService;
import com.sdi.business.impl.EjbRating.LocalRatingService;
import com.sdi.business.impl.EjbRating.RemoteRatingService;
import com.sdi.business.impl.classes.rating.EliminarRating;
import com.sdi.business.impl.classes.rating.ListadoRatings;
import com.sdi.model.Rating;

@Stateless
@WebService(name="RatingService")
public class EjbRatingService implements RatingService,LocalRatingService,RemoteRatingService{

	@Override
	public List<Rating> getRatings() {
		return new ListadoRatings().run();
	}

	@Override
	public List<Rating> getRatingsByTrip(long idTrip) {
		return new ListadoRatings().run();
	}

	@Override
	public List<Rating> getRatingsByUser(long idUser) {
		return null;
	}

	@Override
	public boolean eliminarRating(long id) {
		return new EliminarRating().run(id);
		
	}
	
	

}
