package com.sdi.business.impl.classes.Application;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;

public class SolicitarPlaza {
	
	public Application run(Long idUsuario, Long idViaje){
		
		Application peticion = new Application(idUsuario, idViaje);
		Factories.persistence.newApplicationDao().save(peticion);
		
		return peticion;
	}

}
