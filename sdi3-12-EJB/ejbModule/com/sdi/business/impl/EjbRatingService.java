package com.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import com.sdi.business.RatingService;
import com.sdi.business.impl.classes.rating.ListadoRatings;
import com.sdi.model.Rating;

@Stateless
@TransactionAttribute()
public class EjbRatingService implements RatingService{

	@Override
	public List<Rating> getRatings() {
		return new ListadoRatings().run();
	}

	@Override
	public List<Rating> getRatingsByTrip(long idTrip) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rating> getRatingsByUser(long idUser) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
