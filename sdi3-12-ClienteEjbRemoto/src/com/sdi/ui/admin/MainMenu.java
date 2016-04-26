package com.sdi.ui.admin;

import alb.util.menu.BaseMenu;

import com.sdi.ui.admin.action.DeshabilitarUsuarioAction;
import com.sdi.ui.admin.action.ListarUsuariosAction;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { { "Administrador", null },
				{ "Listado de usuarios", ListarUsuariosAction.class },
				{ "Deshabilitar un usuario", DeshabilitarUsuarioAction.class },
				{ "Listar comentarios y puntuaciones", ListarUsuariosAction.class },
				{ "Eliminar comentarios y puntuaciones", ListarUsuariosAction.class },
				
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
