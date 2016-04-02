package com.sdi.business.impl;

import com.sdi.business.ServicesFactory;
import com.sdi.business.TripService;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public TripService createTripService() {
		return new SimpleTripsService();
	}

}
