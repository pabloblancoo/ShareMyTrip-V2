package com.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.jws.WebService;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.impl.EjbUser.LocalUserService;
import com.sdi.business.impl.EjbUser.RemoteUserService;
import com.sdi.business.impl.classes.User.ActualizarUsuario;
import com.sdi.business.impl.classes.User.IniciarSesion;
import com.sdi.business.impl.classes.User.ListarUsuarios;
import com.sdi.business.impl.classes.User.Registrarse;
import com.sdi.model.User;

@Stateless
@TransactionAttribute()
@WebService(name="UserService")
public class EjbUserService implements RemoteUserService, LocalUserService{

	@Override
	public void registrarse(User user) throws EntityAlreadyExistsException {
		new Registrarse().run(user);
	}

	@Override
	public User iniciarSesion(String login, String password)
			throws BusinessException {
		return new IniciarSesion().run(login, password);
	}

	@Override
	public List<User> listar() {
		return new ListarUsuarios().run();
	}

	@Override
	public void actualizar(User user) {
		new ActualizarUsuario().run(user);
		
	}

}
