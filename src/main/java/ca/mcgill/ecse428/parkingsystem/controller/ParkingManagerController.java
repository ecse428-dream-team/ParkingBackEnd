package ca.mcgill.ecse428.parkingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse428.parkingsystem.model.ParkingManager;
import ca.mcgill.ecse428.parkingsystem.repository.ParkingManagerRepository;

import java.util.List;

/**
 * Endpoints for the Parking Manager class
 *
 */

@RestController
@RequestMapping("/manager")
public class ParkingManagerController {

	@Autowired
	ParkingManagerRepository repository;
	
	/**
	 *Calls method to get a specific parking manager using its id
	 * 
	 * @param id The id of the parking manager to retrieve
	 * @return ResponseEntity<ParkingManager> Return the looked for Parking Manager
	 */
	@GetMapping(path = "/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ParkingManager> getParkingManager(@PathVariable String id) {

		ParkingManager ParkingManagerFound = repository.getParkingManager(id);
		return new ResponseEntity<>(ParkingManagerFound, null, HttpStatus.OK);
	}

	/**
	 *Calls method to get a list of all Parking Managers
	 * 
	 * @return ResponseEntity<List<ParkingManager>> Return a list of all existing parking managers
	 */
	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingManager>> getParkingManagers() {
		List<ParkingManager> ParkingManagers = repository.getParkingManagers();
		return new ResponseEntity<>(ParkingManagers, null, HttpStatus.OK);
	}

}
