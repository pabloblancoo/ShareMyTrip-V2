package com.sdi.business.impl.classes.rating;

import com.sdi.infrastructure.Factories;

public class EliminarRating {

	public boolean run(long id) {
		try {
			Factories.persistence.newRatingDao().delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
