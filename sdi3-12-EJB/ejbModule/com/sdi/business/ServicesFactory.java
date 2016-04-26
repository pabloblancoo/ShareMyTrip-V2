package com.sdi.business;


public interface ServicesFactory {

	TripService getTripService();
	ApplicationService getApplicationService();
	UserService getUserService();
	RatingService getRatingService();

}
