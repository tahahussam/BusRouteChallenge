package main.java.com.goeuro.controller;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	/**
	 * logger for logging
	 */
	final static Logger logger = Logger.getLogger(BusRouteController.class);

	public static void main(String[] args) {
		logger.info("Application Started !");
		SpringApplication.run(Application.class, args);
	}

}