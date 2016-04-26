package com.sdi.business.impl.classes.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.UserDao;
import com.sdi.util.Viajero;

public class BuscarPendientesYViajeros {
	
	public Map<String, Object> run(List<Application> peticiones, Long id, ResourceBundle msgs){
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Viajero> viajeros = new ArrayList<>();
		List<Viajero> pendientes = new ArrayList<>();
		
		SeatDao sd = Factories.persistence.newSeatDao();
		UserDao ud = Factories.persistence.newUserDao();
		Trip viaje = Factories.persistence.newTripDao().findById(id);
		
		Viajero.msgs = msgs;
		
		for (Application peticion : peticiones) {
			Seat plaza = sd.findByUserAndTrip(peticion.getUserId(), id);
			User user = ud.findById(peticion.getUserId());

			Viajero viajero = new Viajero(user, viaje, peticion, plaza);

			if (viajero.getStatus().equals(msgs.getString("ownTripAccepted"))) {
				viajeros.add(viajero);
			}

			pendientes.add(viajero);
		}
		
		mapa.put("viajeros", viajeros);
		mapa.put("pendientes", pendientes);
		
		return mapa;
	}

}
