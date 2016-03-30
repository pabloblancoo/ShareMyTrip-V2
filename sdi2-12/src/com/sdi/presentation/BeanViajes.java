package com.sdi.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.UserDao;
import com.sdi.util.Viajero;

@ManagedBean(name="viajes")
@SessionScoped
public class BeanViajes {
	
	List<Trip> viajes;
	Long lastUpdate;
	Trip viaje;
	User promotor;
	List<Viajero> viajeros;
	List<Viajero> pendientes;
	List<Application> peticiones;
	
	private ResourceBundle msgs = FacesContext.getCurrentInstance()
			.getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs");

	public List<Trip> getViajes() {
		promotor = null;
		viajeros = new ArrayList<>();
		
		if(viajes == null || System.currentTimeMillis() - lastUpdate > (1*60)*1000){
			lastUpdate = System.currentTimeMillis();
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
	
	/**
	 * Metodo que se encarga de cargar el resto de informacion sobre un viaje
	 * (promotor, otros viajeros)
	 */
	public void loadMoreInfo(){
		UserDao ud = Factories.persistence.newUserDao();
		SeatDao sd = Factories.persistence.newSeatDao();
		
		System.out.println("Loading cosas...");
		
		promotor = ud.findById(viaje.getPromoterId());
		System.out.println("Promotor: " + promotor.getName());
		
		//---------------------
		viajeros = new ArrayList<>();
		pendientes = new ArrayList<>();
		
		peticiones = Factories.persistence.newApplicationDao().findByTripId(viaje.getId());
		
		for(Application peticion: peticiones){
			Seat plaza = sd.findByUserAndTrip(peticion.getUserId(), viaje.getId());
			User user = ud.findById(peticion.getUserId());
			
			Viajero viajero = new Viajero(user, viaje, peticion, plaza);
			
			if(viajero.getStatus().equals(msgs.getString("ownTripAccepted"))){
				viajeros.add(viajero);
			}
			
			pendientes.add(viajero);
		}
		
		System.out.println("Loaded " + viajeros.size() + " viajeros");
		
	}

	public List<Viajero> getPendientes() {
		return pendientes;
	}

	public void setPendientes(List<Viajero> pendientes) {
		this.pendientes = pendientes;
	}

	public User getPromotor() {
		return promotor;
	}

	public void setPromotor(User promotor) {
		this.promotor = promotor;
	}

	public List<Viajero> getViajeros() {
		return viajeros;
	}

	public void setViajeros(List<Viajero> viajeros) {
		this.viajeros = viajeros;
	}
	
	/**
	 * Metodo que sirve para comprobar si se debe mostrar el boton de solicitar
	 * plaza en un viaje
	 * @return boolean true para mostrarlo, false para ocultarlo
	 */
	public boolean mostrarSolicitarPlaza(){
		BeanSettings bs = (BeanSettings) FacesContext.getCurrentInstance().getExternalContext()
									.getSessionMap().get("settings");
		
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
	
	/**
	 * Método que inserta una solicitud para el viaje actual y el usuario que 
	 * ha iniciado sesión
	 */
	public void solicitarPlaza(){
		
		BeanSettings bs = (BeanSettings) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("settings");
		FacesMessage msg = null;
		
		Application peticion = new Application(bs.getUsuario().getId(), viaje.getId());
		if(mostrarSolicitarPlaza()){
			Factories.persistence.newApplicationDao().save(peticion);
			peticiones.add(peticion);
			
			System.out.println("Usuario [" + bs.getUsuario().getLogin() + "] solicito plaza en el viaje [id:" + viaje.getId() +"]");
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msgs.getString("tripApplyInfo"));
		}
		else{
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msgs.getString("tripApplyError"));
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
	
	public boolean isPromotorViaje(){
		return ((BeanSettings) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("settings")).getUsuario().getId().equals(viaje.getPromoterId());
	}

}
