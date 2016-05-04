package com.sdi.business.impl.classes.rating;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Rating;

public class ListadoRatings {

	public List<Rating> run() {
		return Factories.persistence.newRatingDao().findAll();

	}
	
	public List<Rating> run(Long id){
		return Factories.persistence.newRatingDao().findByTripId(id);
	}
	
}
