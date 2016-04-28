package com.sdi.ui.admin;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

import com.sdi.util.LogConfig;

public class MainMenu extends BaseMenu {
	
	public static final String REST_SERVICE_URL =
			"http://localhost:8280/sdi3-12-WEB/rest/ServiceRst/";
	

	public MainMenu() {
		menuOptions = new Object[][] {
				{ "Administrador", null },
				{ "Conectarse al grupo de un viaje", NotYetImplementedAction.class },
		};
	}

	public static void main(String[] args) {
		
		LogConfig.config();
		
		new MainMenu().execute();
		
	}

}
