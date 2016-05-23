package main;


import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Start {

	public static void main(String[] args) {
		
		Logger log = LoggerFactory.getLogger(Start.class);
		log.info("Starting on '{}'", Instant.now());
	}
}
