package com.spring.logging.component;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.logging.model.logs;
import com.spring.logging.service.LogService;

@Component
public class RabbitMqComp {
	
	@Autowired
	LogService logService;
	
	@RabbitListener(queues = "Q4")
	public void handleMessage(String message) throws JsonMappingException, JsonProcessingException {
	//	StringBuffer sb=new StringBuffer(message);
	    System.out.println("Received message: " + message);
	    ObjectMapper mapper = new ObjectMapper();
        // read the json strings and convert it into JsonNode
        JsonNode node = mapper.readTree(message);
        System.out.println(node.get("state").asText());
        System.out.println(node.get("log_message").asText());
        System.out.println(node.get("source_ip").asText());
        System.out.println(node.get("request_url").asText());
        System.out.println(node.get("put_date").asText());
        logs l=new logs(
        		node.get("state").asText(),
                node.get("log_message").asText(),
                "",
                "",
                node.get("source_ip").asText(),
                node.get("request_url").asText(),
                node.get("put_date").asText(),
                node.get("sno").asInt());
        logService.save(l);  
	}
}
