package com.sdi.ui.user.action;

import javax.jms.JMSException;

import com.sdi.business.EnviarMensaje;
import com.sdi.business.LeerMensajes;
import com.sdi.util.Contexto;

import alb.util.console.Console;
import alb.util.menu.Action;

public class GrupoAction implements Action {

	@Override
	public void execute() throws Exception {
		if (Contexto.trip != null) {

			Console.println("Escriba 'exit' para salir del grupo");
			try {
				LeerMensajes recibir = new LeerMensajes();
				String mensaje = Console
						.readString("Has entrado en el chat del viaje "
								+ Contexto.trip.getId());

				EnviarMensaje enviar = new EnviarMensaje();
				while (!mensaje.equals("exit")) {
					enviar.enviarMensaje(Contexto.trip.getId(),
							Contexto.usuario.getId(), mensaje);
					mensaje = Console.readString();
				}
				recibir.close();
				enviar.close();
			} catch (JMSException e) {
				System.out
						.println("Ha ocurrido un error la ultima vez que iniciaste sesion. Intentelo mas tarde");
			}
		} else
			Console.println("No estas conectado a ningun grupo");
	}

}
