package com.spring.logging.component;

import java.util.Enumeration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.mq.MQMessage;
import com.spring.logging.model.logs;
import com.spring.logging.service.LogService;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;

@Component
public class IbmmqComp {

	@Autowired
	LogService logService;

	@Autowired
	private JmsTemplate jmsTemplate;

	@JmsListener(destination = "q4")
    public void receiveMessage(Message message) throws JsonMappingException, JsonProcessingException 
		{
		Message m1=message;
			try {
			
				System.out.println(m1.getBody(String.class));
			    System.out.println("Message ID: " + m1.getJMSMessageID().toString());
	            System.out.println("Correlation ID: " + Optional.ofNullable(m1.getJMSCorrelationID()));
	            System.out.println("Destination: " + Optional.ofNullable(m1.getJMSDestination()));
	            System.out.println("Timestamp: " + Optional.ofNullable(m1.getJMSDeliveryTime()));
	            System.out.println(getText(m1.getStringProperty("JMS_IBM_PutDate")));
	            System.out.println(getText(m1.getStringProperty("JMS_IBM_PutTime")));
	            ObjectMapper mapper = new ObjectMapper();
                // read the json strings and convert it into JsonNode
                JsonNode node = mapper.readTree(m1.getBody(String.class));
                System.out.println(node.get("state").asText());
                System.out.println(node.get("log_message").asText());
                System.out.println(node.get("source_ip").asText());
                System.out.println(node.get("request_url").asText());
                System.out.println(node.get("put_date").asText());
                logs l=new logs(
                		node.get("state").asText(),
                        node.get("log_message").asText(),
                        Optional.ofNullable(m1.getJMSCorrelationID()).toString(),
                        m1.getJMSMessageID().toString(),
                        node.get("source_ip").asText(),
                        node.get("request_url").asText(),
                        node.get("put_date").asText(),
                        node.get("sno").asInt());
                logService.save(l);  
	           // System.out.println(m1.toString());
			}
			catch(Exception ex)
			{ex.printStackTrace();}
		}
	
	
	private static String getText(String txt) {
		   Optional<String> text = Optional.ofNullable(txt);
		   if(text.isPresent())
		   {return text.get();}
		   else
		   {return null;}
		}
	
	}
	
/*	@JmsListener(destination = "q4")
    public void receiveMessage(MQMessage message) throws JsonMappingException, JsonProcessingException 
		{
		MQMessage msg=message;
		try {
			
			System.out.println(message.applicationOriginData);
		    System.out.println("Message ID: " +Optional.ofNullable(msg.messageId.toString()));
            System.out.println("Correlation ID: " +Optional.ofNullable(msg.correlationId.toString()));
            System.out.println("Destination: " +Optional.ofNullable(msg.putApplicationName));
            System.out.println("Timestamp: " +Optional.ofNullable(msg.putDateTime));
     
		}
		catch(Exception ex)
		{ex.printStackTrace();}
		}
}
		
	
	/*@JmsListener(destination = "q4")
    public void receiveMessage(Message message) throws JsonMappingException, JsonProcessingException {

		try {
			
			System.out.println(message.getBody(String.class));
            // Read standard JMS headers
            String messageId = message.getJMSMessageID();
            String correlationId = message.getJMSCorrelationID();
            String destination = message.getJMSDestination().toString();
            long timestamp = message.getJMSTimestamp();

            System.out.println("Message ID: " + messageId);
            System.out.println("Correlation ID: " + correlationId);
            System.out.println("Destination: " + destination);
            System.out.println("Timestamp: " + timestamp);
            
//            (String state, String log_message, String correlation_id, String message_id, String source_ip,
//        			String request_url, String put_date)
            // Check if the message is a TextMessage
            if (message instanceof TextMessage) {
                String body = ((TextMessage) message).getText();
                System.out.println("Message Body: " + body);

        /*		ObjectMapper mapper = new ObjectMapper();
                // read the json strings and convert it into JsonNode
                JsonNode node = mapper.readTree(body);
                
                logs l=new logs(
                		node.get("state").asText(),
                        node.get("log_message").asText(),
                        correlationId,
                        messageId,
                        node.get("source_ip").asText(),
                        node.get("request_url").asText(),
                        node.get("put_date").asText(),1);
                logService.save(l);  
                
            }  

       
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }*/
	
	

