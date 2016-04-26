package com.sdi.business.impl.classes.rating;

import java.util.ArrayList;
import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Rating;

public class ListadoRatings {

	public List<Rating> run(){
		List<Rating> ratings = new ArrayList<>();
		ratings = Factories.persistence.newRatingDao().findAll();
		return null;
		
	}
}
