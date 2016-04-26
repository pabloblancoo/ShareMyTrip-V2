package com.sdi.business.impl;

import com.sdi.business.RatingService;
import com.sdi.business.ServiceFactory;
import com.sdi.business.UserService;

public class SimpleServiceFactory implements ServiceFactory{

	@Override
	public RatingService getRatingService() {
		return new SimpleRatingService();
	}

	@Override
	public UserService getUserService() {
		return new SimpleUserService();
	}

}
