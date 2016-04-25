package com.sdi.business.impl.classes.Application;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;

public class BuscarSolicitudes {
	
	public List<Application> run(Long id){
		return Factories.persistence.newApplicationDao().findByTripId(id);
	}

}
