package com.sdi.presentation.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.model.Trip;
import com.sdi.model.User;

public interface ServiceRest {

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{idUser}")
	public List<Trip> listarViajesUsuario(@PathParam("idUser") long idUser);
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{idViaje}")
	public List<User> listaUsuariosPendientes(@PathParam("idViaje") long idViaje);
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{idViaje,idUsuario}")
	public void aceptarViajero(@PathParam("idViaje") long idViaje, @PathParam("idUsuario") long idUsuario);

}
