package com.sdi.ui.user;

import alb.util.console.Console;
import alb.util.menu.BaseMenu;

import com.sdi.ui.user.action.ConectarseAction;
import com.sdi.ui.user.action.GrupoAction;
import com.sdi.ui.user.action.IniciarSesionAction;
import com.sdi.util.Contexto;
import com.sdi.util.LogConfig;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] {
				{ "Usuario - " + Contexto.usuario.getLogin(), null },
				{ "Conectarse al grupo de un viaje", ConectarseAction.class },
				{ "Hablar por el grupo", GrupoAction.class }, };
	}

	public static void main(String[] args) {

		LogConfig.config();
		boolean signIn = false;
		while (!signIn) {
			try {
				new IniciarSesionAction().execute();
				signIn = true;
			} catch (Exception e) {
				Console.println("Usuario y/o password incorrecto.");
				signIn = false;
			}
		}
		if (Contexto.usuario != null) {
			new MainMenu().execute();

		}

	}

}
