package com.sdi.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.util.Viajero;

@ManagedBean(name = "viajes")
@SessionScoped
public class BeanViajes {

	List<Trip> viajes;
	Trip viaje;
	User promotor;
	List<Viajero> viajeros;
	List<Viajero> pendientes;
	List<Application> peticiones;

	public List<Trip> getViajes() {
		FacesMessage msg = null;
		ResourceBundle msgs = FacesContext.getCurrentInstance()
				.getApplication()
				.getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
		promotor = null;
		viajeros = new ArrayList<>();

		List<Trip> viajesDB = null;
		try {
			viajesDB = Factories.services.getTripService().getViajesConPlazasYSinCerrar();
		} catch (Exception e) {
			System.out.println("Error al cargar los viajes");
		}

		if(viajesDB == null){
			System.out.println("LA BASE DE DATOS NO ESTA FUNCIONANDO, CONTACTE CON EL ADMINISTRADOR");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					msgs.getString("errorDBNotWorks"));

			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		}
		if ((viajes == null) ||(viajesDB != null && viajes.size() != viajesDB.size())) {
			viajes = viajesDB;
			System.out.println("Viajes cargados: " + viajes.size());
		}

		return viajes;
	}

	public void setViajes(List<Trip> viajes) {
		this.viajes = viajes;
	}

	public Trip getViaje() throws IOException {
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
	@SuppressWarnings("unchecked")
	public void loadMoreInfo() {
		ResourceBundle msgs = FacesContext.getCurrentInstance()
				.getApplication()
				.getResourceBundle(FacesContext.getCurrentInstance(), "msgs");

		System.out.println("Cargando datos del viaje...");

		promotor = Factories.services.getTripService().findPromotor(viaje.getPromoterId());
		System.out.println("Promotor: " + promotor.getName());

		// ---------------------

		peticiones = Factories.services.getApplicationService().buscarSolicitudes(viaje.getId());
		Map<String, Object> mapa = Factories.services.getApplicationService().buscarPendientesYViajeros(peticiones, viaje.getId(), msgs);
		
		viajeros = (List<Viajero>) mapa.get("viajeros");
		pendientes = (List<Viajero>) mapa.get("pendientes");

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
	 * 
	 * @return boolean true para mostrarlo, false para ocultarlo
	 */
	public boolean mostrarSolicitarPlaza() {
		BeanSettings bs = (BeanSettings) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("settings");

		if (bs.getUsuario() == null) {
			return false;
		}
		if (bs.getUsuario().getLogin().equals(promotor.getLogin())) {
			return false;
		}
		for (Application peticion : peticiones) {
			if (bs.getUsuario().getId().equals(peticion.getUserId())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Método que inserta una solicitud para el viaje actual y el usuario que ha
	 * iniciado sesión
	 */
	public void solicitarPlaza() {
		ResourceBundle msgs = FacesContext.getCurrentInstance()
				.getApplication()
				.getResourceBundle(FacesContext.getCurrentInstance(), "msgs");

		BeanSettings bs = (BeanSettings) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("settings");
		FacesMessage msg = null;
		
		if (mostrarSolicitarPlaza()) {
			Application peticion = Factories.services
					.getApplicationService()
					.solicitarPlaza(bs.getUsuario().getId(), viaje.getId());
			peticiones.add(peticion);

			System.out
					.println("Usuario [" + bs.getUsuario().getLogin()
							+ "] solicito plaza en el viaje [id:"
							+ viaje.getId() + "]");
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
					msgs.getString("tripApplyInfo"));
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					msgs.getString("tripApplyError"));
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	/**
	 * Metodo que devuelve true si el usuario con sesion iniciada es el promotor
	 * 
	 * @return boolean
	 */
	public boolean isPromotorViaje() {
		BeanUsuario u = ((BeanSettings) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("settings"))
				.getUsuario();

		if (u == null) {
			return false;
		}

		return u.getId().equals(viaje.getPromoterId());
	}

	/**
	 * Metodo que acepta a un viajero en el viaje
	 * 
	 * @param viajero
	 */
	public void accept(Viajero viajero) {
		
		Viajero viajeroU = Factories.services.getApplicationService().accept(viajero, viaje);
		
		viajeros.add(viajeroU);

		System.out.println("Plaza confirmada para "
				+ viajero.getUser().getName() + " en el viaje [id:"
				+ viaje.getId() + "]");

	}

	/**
	 * Metodo que excluye a un viajero en el viaje
	 * 
	 * @param viajero
	 */
	public void exclude(Viajero viajero) {
		
		viaje = Factories.services.getApplicationService().exclude(viajero, viaje);
		
		for (int i = 0; i < viajeros.size(); i++) {
			if (viajeros.get(i).getUser().getId()
					.equals(viajero.getUser().getId())) {
				viajeros.remove(i);
			}
		}

		System.out.println("Plaza excluida para " + viajero.getUser().getName()
				+ " en el viaje [id:" + viaje.getId() + "]");

	}

}
