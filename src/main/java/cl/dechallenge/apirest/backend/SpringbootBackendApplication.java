package cl.dechallenge.apirest.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class SpringbootBackendApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringbootBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
		log.info("Start DE Challenge App...");
	}
}
