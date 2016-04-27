package com.sdi.business.impl;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.sdi.business.impl.EjbApplication.LocalApplicationService;
import com.sdi.business.impl.EjbApplication.RemoteApplicationService;
import com.sdi.business.impl.classes.Application.AceptarSolicitud;
import com.sdi.business.impl.classes.Application.BuscarPendientesYViajeros;
import com.sdi.business.impl.classes.Application.BuscarSolicitudes;
import com.sdi.business.impl.classes.Application.CancelarParticipacion;
import com.sdi.business.impl.classes.Application.ExcluirSolicitud;
import com.sdi.business.impl.classes.Application.ExcluirUsuario;
import com.sdi.business.impl.classes.Application.SolicitarPlaza;
import com.sdi.model.Application;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.util.MisViajesConEstado;
import com.sdi.util.Viajero;

@Stateless
@WebService(name="ApplicationService")
public class EjbApplicationService implements  LocalApplicationService, RemoteApplicationService {

	@Override
	public Viajero accept(Viajero viajero, Trip viaje) {
		return new AceptarSolicitud().run(viajero, viaje);
	}
	
	@Override
	public void acceptarUsuario(long idViaje, long idViajero){
		new AceptarSolicitud().run(idViaje, idViajero);
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

	@Override
	public MisViajesConEstado cancelarParticipacion(MisViajesConEstado trip, Long idUsuario) {
		return new CancelarParticipacion().run(trip, idUsuario);
	}

	@Override
	@WebMethod(operationName="excludeUser")
	public void exclude(User user, Trip viaje) {
		new ExcluirUsuario().run(user, viaje);
		
	}


}
