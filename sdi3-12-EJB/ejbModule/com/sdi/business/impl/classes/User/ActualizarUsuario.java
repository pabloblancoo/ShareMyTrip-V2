package com.sdi.business.impl.classes.User;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;

public class ActualizarUsuario {
	
	public void run(User user){
		Factories.persistence.newUserDao().update(user);
	}

}
