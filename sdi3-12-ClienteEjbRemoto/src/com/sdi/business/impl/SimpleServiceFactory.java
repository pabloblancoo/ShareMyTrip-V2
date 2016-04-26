package com.sdi.business.impl;

import com.sdi.business.RatingService;
import com.sdi.business.ServiceFactory;
import com.sdi.business.UserServiceCli;

public class SimpleServiceFactory implements ServiceFactory{

	@Override
	public RatingService getRatingService() {
		return new SimpleRatingService();
	}

	@Override
	public UserServiceCli getUserService() {
		return new SimpleUserService();
	}

}
