package com.sdi.presentation;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

@ManagedBean(name="viajes")
@SessionScoped
public class BeanViajes {
	
	List<Trip> viajes;
	Trip viaje;

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

	public Trip getViaje() {
		return viaje;
	}

	public void setViaje(Trip viaje) {
		this.viaje = viaje;
		loadMoreInfo();
	}
	
	public void loadMoreInfo(){
		System.out.println("Loading cosas...");
	}

}
