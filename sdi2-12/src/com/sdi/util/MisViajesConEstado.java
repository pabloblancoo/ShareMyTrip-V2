package com.sdi.util;

import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import com.sdi.model.Trip;
import com.sdi.model.TripStatus;

public class MisViajesConEstado {
	
	private Trip viaje;
	private String relacion;
	private boolean seleccionado;
		
	public MisViajesConEstado(Trip viaje, String relacion) {
		ResourceBundle msgs = FacesContext.getCurrentInstance()
				.getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
		
		this.viaje = viaje;
		if(!viaje.getStatus().equals(TripStatus.CANCELLED)){
			this.relacion = relacion;
		}
		else{
			this.relacion = msgs.getString("ownTripCancel");
		}
	}

	public Trip getViaje() {
		return viaje;
	}

	public String getRelacion() {
		return relacion;
	}
	
	public String isCancelable(){
		ResourceBundle msgs = FacesContext.getCurrentInstance()
				.getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
		
		if(!viaje.getClosingDate().after(new Date())){
			return null;
		}
		if(viaje.getStatus().equals(TripStatus.OPEN)){
			if(relacion.equals(msgs.getString("tripPromoter"))){
				return  msgs.getString("ownTripCancelTrip");
			}
			else{
				return msgs.getString("ownTripCancelSeat");
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return "Viaje: " + viaje.toString() + ", relacion: " + relacion;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}


}
