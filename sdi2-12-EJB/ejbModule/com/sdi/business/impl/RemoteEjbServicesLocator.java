package com.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.ApplicationService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TripService;
import com.sdi.business.UserService;

public class RemoteEjbServicesLocator implements ServicesFactory {

	private static final String USER_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi2-12/" + "sdi2-12-EJB/" + "EjbUserService!"
			+ "com.sdi.business.impl.RemoteUserService";

	@Override
	public TripService createTripService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationService createApplicationService() {
		// TODO Auto-generated method stub
		return null;
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

}
