package com.sdi.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.sdi.model.Trip;

@ManagedBean(name = "trip")
public class BeanTrip extends Trip implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeanTrip() {
		iniciaTrip(null);
	}
	
	public void setTrip(Trip trip){
		//TODAS LAS DEMAS PROPIEDADES IRIAN AQUI
		setId(trip.getId());
		//....
	}
	
	private void iniciaTrip(Object object) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		
		setId(null);
		setComments(bundle.getString("comentarios"));
		//Seguir añadiendo propiedades
		
	}

	
	
	

}
