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
	private TopicSubscriber subscriber;

	public LeerMensajes() throws JMSException {

		initialize();
	}

	private void initialize() throws JMSException {
		TopicConnectionFactory factory = (TopicConnectionFactory) Jndi
				.find(Contexto.JMS_CONNECTION_FACTORY);
		Topic topic = (Topic) Jndi.find(Contexto.RECIEVE_TOPIC);
			con = factory.createTopicConnection("sdi", "password");
			con.setClientID(Contexto.usuario.getId() + "");

			session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			subscriber = session.createDurableSubscriber(topic,
					Contexto.usuario.getId() + "");
			subscriber.setMessageListener(new ClienteListener());
			con.start();

	}

	public void close() {
		try {
			con.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
