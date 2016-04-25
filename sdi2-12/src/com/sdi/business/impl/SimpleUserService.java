package com.sdi.business.impl;

import com.sdi.business.UserService;
import com.sdi.business.exception.BusinessException;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.impl.classes.User.IniciarSesion;
import com.sdi.business.impl.classes.User.Registrarse;
import com.sdi.model.User;

public class SimpleUserService implements UserService{

	@Override
	public void registrarse(User user) throws EntityAlreadyExistsException {
		new Registrarse().run(user);
	}

	@Override
	public User iniciarSesion(String login, String password)
			throws BusinessException {
		return new IniciarSesion().run(login, password);
	}

}
