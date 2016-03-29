package com.sdi.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
	List<Application> peticiones;

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
		
		peticiones = Factories.persistence.newApplicationDao().findByTripId(viaje.getId());
		
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
	
	/**
	 * Metodo que sirve para comprobar si se debe mostrar el boton de solicitar
	 * plaza en un viaje
	 * @return boolean true para mostrarlo, false para ocultarlo
	 */
	public boolean mostrarSolicitarPlaza(){
		BeanSettings bs = (BeanSettings) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("settings");
		
		if(bs.getUsuario() == null){
			return false;
		}
		if(bs.getUsuario().getLogin().equals(promotor.getLogin())){
			return false;
		}
		for(Application peticion : peticiones){
			if(bs.getUsuario().getId().equals(peticion.getUserId())){
				return false;
			}
		}
		
		return true;
	}
	
	public String solicitarPlaza(){
		BeanSettings bs = (BeanSettings) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("settings");
		
		Application peticion = new Application(bs.getUsuario().getId(), viaje.getId());
		if(mostrarSolicitarPlaza()){
			Factories.persistence.newApplicationDao().save(peticion);
			peticiones.add(peticion);
			
			System.out.println("Usuario [" + bs.getUsuario().getLogin() + "] solicito plaza en el viaje [id:" + viaje.getId() +"]");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Plaza solicitada correctamente"));
		}
		
		return "exito";
	}

}
