package com.sdi.util;

import java.util.Date;

import com.sdi.model.Trip;

public class MisViajesConEstado {
	
	private Trip viaje;
	private String relacion;
	
	public MisViajesConEstado(Trip viaje, String relacion) {
		this.viaje = viaje;
		this.relacion = relacion;
	}

	public Trip getViaje() {
		return viaje;
	}

	public String getRelacion() {
		return relacion;
	}
	
	public boolean isCancelable(){
		return viaje.getClosingDate().after(new Date()) && !relacion.equals("Promotor");
	}
	
	@Override
	public String toString() {
		return "Viaje: " + viaje.toString() + ", relacion: " + relacion;
	}

}
