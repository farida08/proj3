package fr.isika.cdi8.projet3isika;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication 
public class MyApp extends SpringBootServletInitializer  {
	
	public static void main(String[] args) {
		SpringApplication.run(MyApp.class, args);
		
//		SpringApplication app = new SpringApplication(MyApp.class);
//		app.setAdditionalProfiles("initData");
		System.out.println("http://localhost:8080/myJsfSpringBootApp");
	}

}
