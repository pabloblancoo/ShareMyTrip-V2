package com.sdi.business.impl.classes.User;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.persistence.UserDao;

public class Registrarse {
	
	public void run(User user) throws EntityAlreadyExistsException{
		
		UserDao ud = Factories.persistence.newUserDao();

		if (ud.findByLogin(user.getLogin()) != null) {
			throw new EntityAlreadyExistsException();
		}

		ud.save(user);
		
	}

}
