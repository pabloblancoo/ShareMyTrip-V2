package com.sdi.business.impl.classes.User;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;

public class BuscarUsuariosViaje {

	public List<User> run(long idViaje){
		return Factories.persistence.newUserDao().findUsersByTrip(idViaje);
	}
}
