package com.sdi.ui.user;

import alb.util.console.Console;
import alb.util.menu.BaseMenu;

import com.sdi.modelo.User;
import com.sdi.ui.user.action.ConfirmarPasajerosAction;
import com.sdi.ui.user.action.IniciarSesionAction;
import com.sdi.ui.user.action.ListarViajesAction;
import com.sdi.util.LogConfig;

public class MainMenu extends BaseMenu {
	
	public static final String REST_SERVICE_URL =
			"http://localhost:8280/sdi3-12-WEB/rest/ServiceRst/";
	
	public static User usuario;

	public MainMenu() {
		menuOptions = new Object[][] {
				{ "Usuario - " + usuario.getLogin(), null },
				{ "Listar viajes", ListarViajesAction.class },
				{ "Confirmar pasajeros de un viaje", ConfirmarPasajerosAction.class },
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
	}

}
