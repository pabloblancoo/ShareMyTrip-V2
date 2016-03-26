package com.sdi.presentation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.sdi.model.Trip;

public class BeanTrips implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{trip}")
	private BeanTrip trip;

	public BeanTrip getTrip() {
		return trip;
	}

	public void setTrip(BeanTrip trip) {
		this.trip = trip;
	}
	
	@PostConstruct
	public void init() {
		System.out.println("BeanAlumnos - PostConstruct");
		// Buscamos el alumno en la sesión. Esto es un patrón factoría
		// claramente.
		trip = (BeanTrip) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("trip"));
		// si no existe lo creamos e inicializamos
		if (trip == null) {
			System.out.println("BeanAlumnos - No existia");
			trip = new BeanTrip();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("trip", trip);
		}
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanAlumnos - PreDestroy");
	}

	private Trip[] trips = null;

	public Trip[] getTrips() {
		return (trips);
	}

	public void setTrip(Trip[] trips) {
		this.trips = trips;
	}

	public void iniciaAlumno(ActionEvent event) {
//		alumno.setId(null);
//		alumno.setIduser("IdUser");
//		alumno.setNombre("Nombre");
//		alumno.setApellidos("Apellidos");
//		alumno.setEmail("email@domain.com");
	}
	
	public String listado() {
//		AlumnosService service;
//		try {
//			// Acceso a la implementacion de la capa de negocio
//			// a trav��s de la factor��a
//			service = Factories.services.createAlumnosService();
//			// De esta forma le damos informaci��n a toArray para poder hacer el
//			// casting a Alumno[]
//			alumnos = (Alumno[]) service.getAlumnos().toArray(new Alumno[0]);
//
//			return "exito"; // Nos vamos a la vista listado.xhtml
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error"; // Nos vamos la vista de error
//		}
//
		return "";
	}
	
	

}
