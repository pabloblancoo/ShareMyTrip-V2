package com.sdi.business;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.sdi.util.Contexto;
import com.sdi.util.Jndi;

public class EnviarMensaje {

	private Connection con;
	private Session session;
	private MessageProducer sender;

	public void enviarMensaje(long idViaje, long idUsuario) {

		initialize();

		for (int i = 0; i < 100; i++) {

			MapMessage msg = createMessage(idViaje, idUsuario);
			if (msg == null)
				System.out.println("Error en el mensaje");
			else {
				try {
					sender.send(msg);
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
		close();
	}

	private void close() {
		try {
			con.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initialize() {
		ConnectionFactory factory = (ConnectionFactory) Jndi
				.find(Contexto.JMS_CONNECTION_FACTORY);
		Destination queue = (Destination) Jndi.find(Contexto.SEND_QUEUE);
		try {
			con = factory.createConnection("sdi", "password");
			session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			sender = session.createProducer(queue);
			con.start();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

	private MapMessage createMessage(long idViaje, long idUsuario) {

		MapMessage msg;
		try {
			msg = session.createMapMessage();

			msg.setString("command", "new");
			msg.setString("iduser", "123456");
			msg.setString("email", "jms@email.es");
			msg.setString("surname", "JMS Surname");
			return msg;
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
