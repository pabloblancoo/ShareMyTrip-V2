package com.sdi.business;

import java.util.List;

import com.sdi.model.Rating;

public interface RatingService {
	
	public List<Rating> getRatings();
	
	public List<Rating> getRatingsByTrip(long idTrip);
	
	public List<Rating> getRatingsByUser(long idUser);
	
	public boolean eliminarRating(long id);

}
