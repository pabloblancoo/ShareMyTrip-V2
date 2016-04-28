package com.sdi.integration;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/SendMgs") })
public class ShareMyTripListener implements MessageListener {

	
	@Override
	public void onMessage(Message msg) {
		
		//Esto recibe el mensaje y hace con ello que quieras
		MapMessage mapMsg = (MapMessage) msg;
		try {
			System.out.println(mapMsg.getString("command"));
			System.out.println(mapMsg.getString("iduser"));
			System.out.println("Mensaje leido correctamente");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		enviarMensajeAViajeros(mapMsg);

	}


	public static final String RECIEVE_TOPIC = "jms/topic/ReceiveMgs";
	
	private void enviarMensajeAViajeros(MapMessage mapMsg) {
		
		//Esto enviaria el mensaje a la cola del grupo
		
		
	}

}
