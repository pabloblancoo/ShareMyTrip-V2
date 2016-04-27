package com.sdi.ui.admin;

import alb.util.menu.BaseMenu;

import com.sdi.ui.admin.action.DeshabilitarUsuarioAction;
import com.sdi.ui.admin.action.EliminarComentariosAction;
import com.sdi.ui.admin.action.ListarComentariosAction;
import com.sdi.ui.admin.action.ListarUsuariosAction;
import com.sdi.util.LogConfig;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { { "Administrador", null },
				{ "Listado de usuarios", ListarUsuariosAction.class },
				{ "Deshabilitar un usuario", DeshabilitarUsuarioAction.class },
				{ "Listar comentarios y puntuaciones", ListarComentariosAction.class },
				{ "Eliminar comentarios y puntuaciones", EliminarComentariosAction.class },
				
		};
	}

	public static void main(String[] args) {
		
		LogConfig.config();
		
		new MainMenu().execute();
	}

}
