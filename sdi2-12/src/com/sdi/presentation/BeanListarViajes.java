package com.sdi.presentation;

import java.util.List;

import javax.faces.bean.ManagedBean;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

@ManagedBean(name="listarViajes")
public class BeanListarViajes {
	
	List<Trip> viajes;

	public List<Trip> getViajes() {
		if(viajes == null){
			viajes = Factories.persistence.newTripDao().findAllOpenAndPaxAvailables();
			System.out.println("Viajes cargados: " + viajes.size());
		}
		return viajes;
	}

	public void setViajes(List<Trip> viajes) {
		this.viajes = viajes;
	}

}
