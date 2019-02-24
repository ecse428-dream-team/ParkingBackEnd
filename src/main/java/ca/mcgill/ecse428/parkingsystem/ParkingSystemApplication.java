package ca.mcgill.ecse428.parkingsystem;

import ca.mcgill.ecse428.parkingsystem.model.ParkingManager;
import ca.mcgill.ecse428.parkingsystem.repository.ParkingManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ParkingSystemApplication {

    @Autowired
    ParkingManagerRepository pmr;

	public static void main(String[] args) {
		SpringApplication.run(ParkingSystemApplication.class, args);
	}

	@Bean
    public void createSingletonParkingManager() {
	    try {
	        ParkingManager pm = new ParkingManager("1");
	        pmr.addManager(pm);
        } catch (Exception e) {
	        System.out.println("Manager instance already exists");
        }
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

