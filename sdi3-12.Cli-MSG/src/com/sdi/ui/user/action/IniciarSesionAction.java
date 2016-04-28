package com.sdi.ui.user.action;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.modelo.User;
import com.sdi.ui.user.MainMenu;

public class IniciarSesionAction implements Action{

	@Override
	public void execute() throws Exception {
		
		User u = new User();
		
		u.setLogin(Console.readString("Usuario"));
		u.setPassword(Console.readString("Password"));
		
		u = ClientBuilder.newClient()
			.target(MainMenu. REST_SERVICE_URL )
			.request()
			.post(Entity.entity(u, MediaType.APPLICATION_JSON))
			.readEntity(User.class);
		
		if(u == null){
			throw new Exception();
		}
		
		MainMenu.usuario = u;
		
	}

}
