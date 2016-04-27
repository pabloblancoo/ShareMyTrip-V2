package com.sdi.business;

import java.util.List;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.model.User;

public interface UserService {
	
	public void registrarse(User user) throws EntityAlreadyExistsException;
	public User iniciarSesion(String login, String password) throws BusinessException;
	public List<User> listar();
	public void actualizar(User user);
	public List<User> getUsuariosViaje(long idViaje);

}
