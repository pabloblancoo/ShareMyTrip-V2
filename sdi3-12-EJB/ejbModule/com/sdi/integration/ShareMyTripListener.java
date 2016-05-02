package com.sdi.integration;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/SendMgs") })
public class ShareMyTripListener implements MessageListener {

	
	@Override
	public void onMessage(Message msg) {
		
		//Esto recibe el mensaje y hace con ello que quieras
		MapMessage mapMsg = (MapMessage) msg;
		try {
			System.out.println("ID viaje: " + mapMsg.getLong("idViaje"));
			System.out.println("ID usuario: " + mapMsg.getLong("idUser"));
			System.out.println("Mensaje: " + mapMsg.getString("mensaje"));
			
//			System.out.println("El mensaje ha llegado al servidor correctamente");
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		enviarMensajeAViajeros(mapMsg);

	}


	public static final String RECIEVE_TOPIC = "jms/topic/ReceiveMgs";
	
	private void enviarMensajeAViajeros(MapMessage mapMsg) {
		
		//Esto enviaria el mensaje a la cola del grupo
		
		List<User> usuarios = new ArrayList<>();
		try {
			usuarios = Factories.services.getUserService().getUsuariosAceptadosPromotorViaje(mapMsg.getLong("idViaje"));
		} catch (JMSException e) {
			e.printStackTrace();
		}
		String idUsuarios = "";
		
		for (int i = 0; i < usuarios.size(); i++) {
			try {
				if(!usuarios.get(i).getId().equals(mapMsg.getLong("idUser"))){
					if(i==0)
						idUsuarios += usuarios.get(i).getId();
					else
						idUsuarios += "-" + usuarios.get(i).getId();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
		try {
			new EnviarMensajeACliente().enviarMensaje(mapMsg.getLong("idViaje"), idUsuarios, mapMsg.getString("mensaje"),mapMsg.getLong("idUser"));
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
