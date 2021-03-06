package com.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.ApplicationService;
import com.sdi.business.RatingService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TripService;
import com.sdi.business.UserService;

public class LocalEjbServicesLocator implements ServicesFactory {

	private static final String USER_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi3-12/" + "sdi3-12-EJB/" + "EjbUserService!"
			+ "com.sdi.business.impl.EjbUser.LocalUserService";
	
	private static final String APPLICATION_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi3-12/" + "sdi3-12-EJB/" + "EjbApplicationService!"
			+ "com.sdi.business.impl.EjbApplication.LocalApplicationService";
	
	private static final String TRIP_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi3-12/" + "sdi3-12-EJB/" + "EjbTripsService!"
			+ "com.sdi.business.impl.EjbTrips.LocalTripsService";

	private static final String RATING_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi3-12/" + "sdi3-12-EJB/" + "EjbRatingService!"
			+ "com.sdi.business.impl.EjbRating.LocalRatingService";

	
	@Override
	public TripService getTripService() {
		try {
			Context ctx = new InitialContext();
			//Refactorizado para comprobar
			//Aqui da el error
			Object o = ctx.lookup(TRIP_SERVICE_JNDI_KEY);
			return (TripService) o;
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
