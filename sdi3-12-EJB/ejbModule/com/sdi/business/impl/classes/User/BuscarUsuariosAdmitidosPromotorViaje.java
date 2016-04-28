package com.sdi.business.impl.classes.User;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;

public class BuscarUsuariosAdmitidosPromotorViaje {
	
	public List<User> run(Long idViaje){
		return Factories.persistence.newUserDao().findUsersAcceptedPromotorByTrip(idViaje);
	}

}
