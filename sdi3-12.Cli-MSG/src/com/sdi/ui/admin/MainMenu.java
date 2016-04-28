package com.sdi.ui.admin;

import alb.util.console.Console;
import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

import com.sdi.modelo.User;
import com.sdi.ui.admin.action.IniciarSesionAction;
import com.sdi.util.LogConfig;

public class MainMenu extends BaseMenu {
	
	public static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	public static final String SEND_QUEUE = "jms/queue/SendMgs";
	public static final String RECIEVE_TOPIC = "jms/topic/ReceiveMgs";
	public static final String REST_SERVICE_URL ="http://localhost:8280/sdi3-12-WEB/rest/ServiceRst/";
	public static User usuario;

	public MainMenu() {
		menuOptions = new Object[][] {
				{ "Administrador", null },
				{ "Conectarse al grupo de un viaje", NotYetImplementedAction.class },
		};
	}

	public static void main(String[] args) {
		
		LogConfig.config();
		
		try {
			new IniciarSesionAction().execute();
		} catch (Exception e) {
			Console.println("Usuario y/o password incorrecto.");
		}
		
		if(usuario != null){
			new MainMenu().execute();

		}
		
		new MainMenu().execute();
		
	}

}
