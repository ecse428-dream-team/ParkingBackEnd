package ca.mcgill.ecse428.parkingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ParkingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingSystemApplication.class, args);
	}
	
	@Bean
	@SuppressWarnings("deprecation")
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurerAdapter() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	        	
	        	String webFrontEnd = "";
	        			
	        	String localhost = "http://localhost:8080";
	        	
	        	registry.addMapping("/**");
//	            registry.addMapping("/**").allowedOrigins(localhost, webFrontEnd);
	        }
	    };
	}

}

