package com.travel;

import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelKarnatakaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelKarnatakaApplication.class, args);
		
//		 if (!GraphicsEnvironment.isHeadless()) {
//	            try {
//	                Desktop.getDesktop().browse(new URI("http://localhost:8080"));
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	            }
//	        } else {
//	            System.out.println("Headless environment - cannot open browser automatically.");
//	        }
	}

}
