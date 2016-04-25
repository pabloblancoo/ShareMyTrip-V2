package com.sdi.business;

public interface ServicesFactory {

	TripService createTripService();
	ApplicationService createApplicationService();
	UserService createUserService();

}
