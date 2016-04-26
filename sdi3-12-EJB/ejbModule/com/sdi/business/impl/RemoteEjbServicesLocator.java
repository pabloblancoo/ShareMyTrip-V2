package com.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.ApplicationService;
import com.sdi.business.RatingService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TripService;
import com.sdi.business.UserService;

public class RemoteEjbServicesLocator implements ServicesFactory {

	private static final String USER_SERVICE_JNDI_KEY = "/"
			+ "sdi3-12/" + "sdi3-12-EJB/" + "EjbUserService!"
			+ "com.sdi.business.impl.EjbUser.RemoteUserService";
	
	private static final String APPLICATION_SERVICE_JNDI_KEY = "/"
			+ "sdi3-12/" + "sdi3-12-EJB/" + "EjbApplicationService!"
			+ "com.sdi.business.impl.EjbApplication.RemoteApplicationService";
	
	private static final String TRIP_SERVICE_JNDI_KEY = "/"
			+ "sdi3-12/" + "sdi3-12-EJB/" + "EjbTripsService!"
			+ "com.sdi.business.impl.EjbTrips.RemoteTripsService";

	private static final String RATING_SERVICE_JNDI_KEY = "/"
			+ "sdi3-12/" + "sdi3-12-EJB/" + "EjbRatingService!"
			+ "com.sdi.business.impl.EjbRating.RemoteRatingService";
	
	@Override
	public TripService getTripService() {
		try {
			Context ctx = new InitialContext();
			return (TripService) ctx.lookup(TRIP_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public ApplicationService getApplicationService() {
		try {
			Context ctx = new InitialContext();
			return (ApplicationService) ctx.lookup(APPLICATION_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public UserService getUserService() {
		try {
			Context ctx = new InitialContext();
			return (UserService) ctx.lookup(USER_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public RatingService getRatingService() {
		try {
			Context ctx = new InitialContext();
			return (RatingService) ctx.lookup(RATING_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

}
