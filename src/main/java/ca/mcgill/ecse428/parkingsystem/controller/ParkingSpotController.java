package ca.mcgill.ecse428.parkingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.parkingsystem.model.ParkingSpot;
import ca.mcgill.ecse428.parkingsystem.repository.ParkingSpotRepository;

@RestController
@RequestMapping("/spot")
public class ParkingSpotController {

	@Autowired
	ParkingSpotRepository repository;
	
	@GetMapping(path = "/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ParkingSpot> getParkingSpot(@PathVariable String id) {
		ParkingSpot ParkingSpotFound = repository.getParkingSpot(id);
		return new ResponseEntity<ParkingSpot>(ParkingSpotFound, null, HttpStatus.OK);
	}

	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> getParkingSpots() {
		List<ParkingSpot> ParkingSpots = repository.getParkingSpots();
		return new ResponseEntity<List<ParkingSpot>>(ParkingSpots, null, HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ParkingSpot addParkingSpot(@RequestBody ParkingSpot ps) {
	    return repository.addParkingSpot(ps);
	}
	
	
	
	// All methods below here pertains to the newly added repository methods for general search and advanced searches
	@GetMapping(path = "/general", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> generalSearch() {
		return new ResponseEntity<List<ParkingSpot>>(repository.generalSearch(), null, HttpStatus.OK);
	}
	
	@GetMapping(path = "/advance/search1/{field}/{value}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> advancedStringFieldSearch(@PathVariable String field, @PathVariable String value) {
		return new ResponseEntity<List<ParkingSpot>>(repository.advancedStringFieldSearch(field, value), null, HttpStatus.OK);
	}
	
	@GetMapping(path = "/advance/search2/{field}/{value}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> advancedFloatFieldSearch(@PathVariable String field, @PathVariable float value) {
		return new ResponseEntity<List<ParkingSpot>>(repository.advancedFloatFieldSearch(field, value), null, HttpStatus.OK);
	}
	
	@GetMapping(path = "/advance/search3/{value}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> advancedStringFieldSearch(@PathVariable int value) {
		return new ResponseEntity<List<ParkingSpot>>(repository.advancedSearchByStreetNum(value), null, HttpStatus.OK);
	}
}
