package com.sdi.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.model.Trip;
import com.sdi.model.User;

@Path("/ServiceRst")
public interface ServiceRest {

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("viajes/{idUser}")
	public List<Trip> listarViajesUsuario(@PathParam("idUser") long idUser);
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("pendientes/{idViaje}")
	public List<User> listaUsuariosPendientes(@PathParam("idViaje") long idViaje);
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	//idViaje, idUsuario
	public void aceptarViajero(List<Long> datos);
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User iniciarSesion(User datos);

}
