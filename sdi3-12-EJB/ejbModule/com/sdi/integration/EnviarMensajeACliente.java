package com.sdi.integration;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

public class EnviarMensajeACliente {
	
	private TopicConnection con;
	private TopicSession session;
	private MessageProducer sender;
	
	public void enviarMensaje(long idViaje, String idUsuarios, String mensaje, long idUser){
		initialize();

		MapMessage msg = createMessage(idViaje, idUsuarios,mensaje,idUser);
		if (msg == null)
			System.out.println("Error en el mensaje");
		else {
			try {
				sender.send(msg);
//				System.out.println("El mensaje ha salido del servidor correctamente");
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
		
		close();
	}


	private void initialize() {
		TopicConnectionFactory factory = (TopicConnectionFactory) Jndi
				.find(Contexto.JMS_CONNECTION_FACTORY);
		Topic topic = (Topic) Jndi.find(Contexto.RECIEVE_TOPIC);
		try {
			con = factory.createTopicConnection("sdi", "password");

			session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			sender = session.createPublisher(topic);
			con.start();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
	
	private MapMessage createMessage(long idViaje, String idUsuario,String mensaje, long idUser) {

		MapMessage msg;
		try {
			msg = session.createMapMessage();

			msg.setLong("idViaje", idViaje);
			msg.setString("idUsers", idUsuario);
			msg.setString("mensaje", mensaje);
			msg.setLong("idUser", idUser);
			return msg;
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void close() {
		try {
			con.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
