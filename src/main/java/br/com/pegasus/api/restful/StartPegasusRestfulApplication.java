package br.com.pegasus.api.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.pegasus.api.restful", "br.com.pegasus.openapi"})
public class StartPegasusRestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartPegasusRestfulApplication.class, args);
	}

}
