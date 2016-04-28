package com.sdi.business.impl.classes.Trip;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

public class ListarViajesUsuarioPromotorOAceptado {

	public List<Trip> run(Long idUser){
		return Factories.persistence.newTripDao().findByUserPromoterOrAccept(idUser);
	}
	
}
