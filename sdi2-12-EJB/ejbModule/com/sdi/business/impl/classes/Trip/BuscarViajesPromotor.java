package com.sdi.business.impl.classes.Trip;

import java.util.List;
import java.util.ResourceBundle;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.util.MisViajesConEstado;

public class BuscarViajesPromotor {

	/**
	 * Metodo que cargar los viajes en los que es promotor el usuario pasado
	 * como parametro
	 * 
	 * @param misViajes
	 *            lista a la que se quieren añadir los viajes
	 * @param idUsuario
	 *            id del usuario del que se buscan los viajes
	 */
	public void run(List<MisViajesConEstado> misViajes, Long idUsuario, ResourceBundle msgs) {
		
		List<Trip> viajes = Factories.persistence.newTripDao()
				.findByPromoterId(idUsuario);
		for (Trip viaje : viajes) {
			misViajes.add(new MisViajesConEstado(viaje, msgs.getString("tripPromoter"), msgs));
		}
		
	}
	
}
