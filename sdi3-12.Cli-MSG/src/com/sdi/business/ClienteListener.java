package com.sdi.business;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/ReceiveMgs") })
public class ClienteListener implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		
		MapMessage mapMsg = (MapMessage) arg0;
		try {
			long idUser = mapMsg.getLong("idUser");
			String mensaje = mapMsg.getString("mensaje");
			
			System.out.println(idUser + " -> " + mensaje);
//			System.out.println(mapMsg.getString("idUsers"));
			
			System.out.println("El mensaje ha llegado al cliente correctamente");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
