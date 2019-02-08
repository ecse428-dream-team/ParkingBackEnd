package ca.mcgill.ecse428.parkingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.parkingsystem.model.ParkingManager;
import ca.mcgill.ecse428.parkingsystem.repository.ParkingManagerRepository;

@RestController
@RequestMapping("/manager")
public class ParkingManagerController {
	
	@Autowired
	ParkingManagerRepository repository;

    @GetMapping
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello", null, HttpStatus.OK);
    }
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ParkingManager createUser(@RequestBody ParkingManager manager) {
	    return repository.addManager(manager);
	}
	
	
}

