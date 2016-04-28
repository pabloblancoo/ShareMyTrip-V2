package com.sdi.business;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import com.sdi.util.Contexto;
import com.sdi.util.Jndi;

public class LeerMensajes {

	private TopicConnection con;
	private TopicSession session;
	private TopicSubscriber subscriber ;

	public void leerMensajes() {

		initialize();
		
		
	}
	
	private void initialize() {
		TopicConnectionFactory factory = (TopicConnectionFactory) Jndi
				.find(Contexto.JMS_CONNECTION_FACTORY);
		Topic topic = (Topic) Jndi.find(Contexto.RECIEVE_TOPIC);
		try {
			con = factory.createTopicConnection("sdi", "password");
			session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			subscriber = session.createSubscriber(topic);
			subscriber.setMessageListener(new ClienteListener());
			con.start();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
	

	private void close() {
		try {
			con.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}