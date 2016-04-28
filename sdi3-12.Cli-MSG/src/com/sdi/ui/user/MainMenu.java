package com.sdi.ui.user;

import alb.util.console.Console;
import alb.util.menu.BaseMenu;

import com.sdi.ui.user.action.ConectarseAction;
import com.sdi.ui.user.action.IniciarSesionAction;
import com.sdi.util.Contexto;
import com.sdi.util.LogConfig;

public class MainMenu extends BaseMenu {
	
	
	public MainMenu() {
		menuOptions = new Object[][] {
				{ "Usuario - " + Contexto.usuario.getLogin(), null },
				{ "Conectarse al grupo de un viaje", ConectarseAction.class },
		};
	}

	public static void main(String[] args) {
		
		LogConfig.config();
		
		try {
			new IniciarSesionAction().execute();
		} catch (Exception e) {
			Console.println("Usuario y/o password incorrecto.");
		}
		
		if(Contexto.usuario != null){
			new MainMenu().execute();

		}
		
		new MainMenu().execute();
		
	}

}
