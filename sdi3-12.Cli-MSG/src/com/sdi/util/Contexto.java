package com.sdi.util;

import com.sdi.modelo.Trip;
import com.sdi.modelo.User;

public class Contexto {
	
	public static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	public static final String SEND_QUEUE = "jms/queue/SendMgs";
	public static final String RECIEVE_TOPIC = "jms/topic/ReceiveMgs";
	public static final String REST_SERVICE_URL ="http://localhost:8280/sdi3-12-WEB/rest/ServiceRst/";
	
	public static User usuario;
	public static Trip trip;


}
