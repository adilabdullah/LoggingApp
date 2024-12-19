package com.spring.logging;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;

import com.spring.logging.component.IbmmqComp;
@SpringBootApplication
@EnableJms
@EnableRabbit
public class LoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggingApplication.class, args);
	/*	
		ApplicationContext context=SpringApplication.run(LoggingApplication.class, args);
		IbmmqComp comp=context.getBean(IbmmqComp.class);
	   
	    try {
	   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
	}

}
