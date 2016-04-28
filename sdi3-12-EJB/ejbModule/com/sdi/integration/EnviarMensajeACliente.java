package com.sdi.integration;

import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

public class EnviarMensajeACliente {
	
	private Connection con;
	private Session session;
	private MessageProducer sender;
	
	public void enviarMensaje(long idViaje, List<Long> idUsuarios, String mensaje){
		initialize();
		
		MapMessage msg = createMessage(idViaje, idUsuarios,mensaje);
		if (msg == null)
			System.out.println("Error en el mensaje");
		else {
			try {
				sender.send(msg);
				System.out.println("El mensaje ha salido del servidor correctamente");
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
		
		close();
	}

	private void initialize() {
		ConnectionFactory factory = (ConnectionFactory) Jndi
				.find(Contexto.JMS_CONNECTION_FACTORY);
		Destination queue = (Destination) Jndi.find(Contexto.RECIEVE_TOPIC);
		try {
			con = factory.createConnection("sdi", "password");
			session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			sender = session.createProducer(queue);
			con.start();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
	
	private MapMessage createMessage(long idViaje, List<Long> idUsuario,String mensaje) {

		MapMessage msg;
		try {
			msg = session.createMapMessage();

			msg.setLong("idViaje", idViaje);
			msg.setObject("idUsers", idUsuario);
			msg.setString("mensaje", mensaje);
			return msg;
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private void close() {
		try {
			con.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
