package ca.mcgill.ecse428.parkingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse428.parkingsystem.model.ParkingManager;
import ca.mcgill.ecse428.parkingsystem.repository.ParkingManagerRepository;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ParkingManagerController {

	@Autowired
	ParkingManagerRepository repository;

	@GetMapping(path = "/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ParkingManager> getParkingManager(@PathVariable String id) {

		ParkingManager ParkingManagerFound = repository.getParkingManager(id);
		return new ResponseEntity<>(ParkingManagerFound, null, HttpStatus.OK);
	}

	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingManager>> getParkingManagers() {
		List<ParkingManager> ParkingManagers = repository.getParkingManagers();
		return new ResponseEntity<>(ParkingManagers, null, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ParkingManager createUser(@RequestBody ParkingManager manager) {
		return repository.addManager(manager);
	}

}
