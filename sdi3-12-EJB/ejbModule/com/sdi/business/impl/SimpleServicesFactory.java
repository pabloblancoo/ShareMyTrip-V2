package com.sdi.business.impl;

import com.sdi.business.ApplicationService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TripService;
import com.sdi.business.UserService;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public TripService getTripService() {
		return new EjbTripsService();
	}

	@Override
	public ApplicationService getApplicationService() {
		return new EjbApplicationService();
	}

	@Override
	public UserService getUserService() {
		return new EjbUserService();
	}

}
