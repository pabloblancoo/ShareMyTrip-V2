package com.sdi.business;

public interface ServiceFactory {
	
	public RatingService getRatingService();
	public UserService getUserService();

}