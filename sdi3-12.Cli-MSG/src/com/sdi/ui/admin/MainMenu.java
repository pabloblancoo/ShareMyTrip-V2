package com.sdi.ui.admin;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

import com.sdi.util.LogConfig;

public class MainMenu extends BaseMenu {
	
	public static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	public static final String SEND_QUEUE = "jms/queue/SendMgs";
	public static final String RECIEVE_TOPIC = "jms/topic/ReceiveMgs";

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
