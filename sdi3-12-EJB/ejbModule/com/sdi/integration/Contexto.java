package com.sdi.integration;

import com.sdi.model.Trip;
import com.sdi.model.User;

public class Contexto {
	
	public static final String JMS_CONNECTION_FACTORY = "java:/ConnectionFactory";
	public static final String RECIEVE_TOPIC = "java:/topic/ReceiveMgs";
	
	public static User usuario;
	public static Trip trip;


}
