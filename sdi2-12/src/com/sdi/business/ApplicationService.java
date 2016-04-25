package com.sdi.business;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.sdi.model.Application;
import com.sdi.model.Trip;
import com.sdi.util.Viajero;

public interface ApplicationService {
	
	public Viajero accept(Viajero viajero, Trip viaje);
	public Trip exclude(Viajero viajero, Trip viaje);
	
	public Application solicitarPlaza(Long idUsuario, Long idViaje);
	
	public List<Application> buscarSolicitudes(Long id);
	public Map<String, Object> buscarPendientesYViajeros(List<Application> peticiones, Long id, ResourceBundle msgs);

}
