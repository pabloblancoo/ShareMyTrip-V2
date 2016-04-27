package com.sdi.ui.admin;

import alb.util.menu.BaseMenu;

import com.sdi.ui.admin.action.ConfirmarPasajerosAction;
import com.sdi.ui.admin.action.ListarViajesAction;
import com.sdi.util.LogConfig;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { { "Administrador", null },
				{ "Listar viajes", ListarViajesAction.class },
				{ "Confirmar pasajeros de un viaje", ConfirmarPasajerosAction.class },
		};
	}

	public static void main(String[] args) {
		
		LogConfig.config();
		
		new MainMenu().execute();
	}

}
