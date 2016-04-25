package com.sdi.business.impl.classes.User;

import com.sdi.business.exception.BusinessException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.util.Encriptador;

public class IniciarSesion {
	
	public User run(String login, String password) throws BusinessException{
		
		User user = Factories.persistence.newUserDao().findByLogin(login,
				Encriptador.encriptar(password));
		
		if(user == null){
			throw new BusinessException();
		}
		
		return user;
		
	}

}
