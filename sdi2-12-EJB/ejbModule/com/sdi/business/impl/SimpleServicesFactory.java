package com.sdi.business.impl;

import com.sdi.business.ApplicationService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TripService;
import com.sdi.business.UserService;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public TripService createTripService() {
		return new SimpleTripsService();
	}

	@Override
	public ApplicationService createApplicationService() {
		return new SimpleApplicationService();
	}

	@Override
	public UserService createUserService() {
		return new EjbUserService();
	}

}
