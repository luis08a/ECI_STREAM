package edu.eci.arsw.eci_stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
public class ECI_StreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECI_StreamApplication.class, args);
	}

}
