package com.sdi.ui.admin;

import alb.util.menu.BaseMenu;

import com.sdi.ui.admin.action.ListarUsuarios;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { { "Administrador", null },
				{ "Listado de usuarios", ListarUsuarios.class },
				{ "Deshabilitar un usuario", ListarUsuarios.class },
				{ "Listar comentarios y puntuaciones", ListarUsuarios.class },
				{ "Eliminar comentarios y puntuaciones", ListarUsuarios.class },
				
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
