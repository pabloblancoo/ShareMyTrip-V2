package com.sdi.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.UserDao;

@ManagedBean(name="viajes")
@SessionScoped
public class BeanViajes {
	
	List<Trip> viajes;
	Trip viaje;
	User promotor;
	List<User> viajeros;

	public List<Trip> getViajes() {
		promotor = null;
		viajeros = new ArrayList<>();
		
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
		UserDao ud = Factories.persistence.newUserDao();
		SeatDao sd = Factories.persistence.newSeatDao();
		
		System.out.println("Loading cosas...");
		
		promotor = ud.findById(viaje.getPromoterId());
		System.out.println("Promotor: " + promotor.getName());
		viajeros = new ArrayList<>();
		
		List<Application> peticiones = Factories.persistence.newApplicationDao().findByTripId(viaje.getId());
		
		for(Application peticion: peticiones){
			Seat plaza = sd.findByUserAndTrip(peticion.getUserId(), viaje.getId());
			if(plaza != null && plaza.getStatus().equals(SeatStatus.ACCEPTED)){
				viajeros.add(ud.findById(plaza.getUserId()));
			}
		}
		
		System.out.println("Loaded " + viajeros.size() + " viajeros");
		
	}

	public User getPromotor() {
		return promotor;
	}

	public void setPromotor(User promotor) {
		this.promotor = promotor;
	}

	public List<User> getViajeros() {
		return viajeros;
	}

	public void setViajeros(List<User> viajeros) {
		this.viajeros = viajeros;
	}

}
