package com.sdi.business.impl.classes.Trip;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.persistence.UserDao;

public class BuscarPromotor {
	
	public User run(Long id){
		
		UserDao ud = Factories.persistence.newUserDao();
		return ud.findById(id);
		
	}

}
