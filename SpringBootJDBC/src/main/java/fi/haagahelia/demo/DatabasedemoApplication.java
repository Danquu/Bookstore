package fi.haagahelia.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.demo.domain.Student;
import fi.haagahelia.demo.domain.StudentDAOImpl;

@SpringBootApplication
public class DatabasedemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DatabasedemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DatabasedemoApplication.class, args);
	}
	  
		
}
