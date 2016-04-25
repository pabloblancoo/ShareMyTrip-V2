package com.sdi.business.impl;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.sdi.business.ApplicationService;
import com.sdi.business.impl.classes.Application.AceptarSolicitud;
import com.sdi.business.impl.classes.Application.BuscarPendientesYViajeros;
import com.sdi.business.impl.classes.Application.BuscarSolicitudes;
import com.sdi.business.impl.classes.Application.ExcluirSolicitud;
import com.sdi.business.impl.classes.Application.SolicitarPlaza;
import com.sdi.model.Application;
import com.sdi.model.Trip;
import com.sdi.util.Viajero;

public class SimpleApplicationService implements ApplicationService {

	@Override
	public Viajero accept(Viajero viajero, Trip viaje) {
		return new AceptarSolicitud().run(viajero, viaje);
	}

	@Override
	public Trip exclude(Viajero viajero, Trip viaje) {
		return new ExcluirSolicitud().run(viajero, viaje);
	}

	@Override
	public Application solicitarPlaza(Long idUsuario, Long idViaje) {
		return new SolicitarPlaza().run(idUsuario, idViaje);
	}

	@Override
	public List<Application> buscarSolicitudes(Long id) {
		return new BuscarSolicitudes().run(id);
	}

	@Override
	public Map<String, Object> buscarPendientesYViajeros(List<Application> peticiones, Long id, ResourceBundle msgs) {
		return new BuscarPendientesYViajeros().run(peticiones, id, msgs);
	}


}
