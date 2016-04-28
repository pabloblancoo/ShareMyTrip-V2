package com.sdi.ui.user.action;

import com.sdi.business.EnviarMensaje;
import com.sdi.business.LeerMensajes;
import com.sdi.util.Contexto;

import alb.util.console.Console;
import alb.util.menu.Action;

public class GrupoAction implements Action{

	@Override
	public void execute() throws Exception {
		if(Contexto.trip != null){

			Console.println("Escriba 'exit' para salir del grupo");
			LeerMensajes recibir = new LeerMensajes();
			String mensaje = Console.readString("Mensaje");
			
			EnviarMensaje enviar = new EnviarMensaje();
			while (!mensaje.equals("exit")){
				enviar.enviarMensaje(Contexto.trip.getId(),Contexto.usuario.getId(), mensaje);
				mensaje = Console.readString("Mensaje");
			}
			recibir.close();
			enviar.close();
		}
		else
			Console.println("No estas conectado a ningun grupo");
	}
	
	

}
