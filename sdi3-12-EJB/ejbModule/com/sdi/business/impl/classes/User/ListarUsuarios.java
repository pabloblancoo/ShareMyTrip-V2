package com.sdi.business.impl.classes.User;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;

public class ListarUsuarios {
	
	public List<User> run(){
		return Factories.persistence.newUserDao().findAll();
	}

}
