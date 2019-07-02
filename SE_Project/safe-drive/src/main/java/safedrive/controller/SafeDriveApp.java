package safedrive.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages={"safedrive.controller","safedrive.services","safedrive.beans"})
public class SafeDriveApp {

	public static void main(String[] args) {
		
		SpringApplication.run(SafeDriveApp.class, args);

	}

}
