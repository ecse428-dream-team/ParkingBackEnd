package ca.mcgill.ecse428.parkingsystem.controller;

import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
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
		ParkingSpot parkingSpotFound = repository.getParkingSpot(id);
		return new ResponseEntity<ParkingSpot>(parkingSpotFound, null, HttpStatus.OK);
	}

	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> getParkingSpots() {
		List<ParkingSpot> parkingSpots = repository.getParkingSpots();
		return new ResponseEntity<List<ParkingSpot>>(parkingSpots, null, HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ParkingSpot addParkingSpot(@RequestBody ParkingSpot ps) {
	    return repository.addParkingSpot(ps);
	}
	
	// All methods below here pertains to the newly added repository methods for general search and advanced searches
	@GetMapping(path = "/partialSearch/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> partialSearchById(@PathVariable String id) {
		List<ParkingSpot> parkingSpots = repository.partialSearchById(id);
		return new ResponseEntity<List<ParkingSpot>>(parkingSpots, null, HttpStatus.OK);
	}
	
	@GetMapping(path = "/partialSearch/streetName/{name}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> partialSearchByStreetName(@PathVariable String name) {
		List<ParkingSpot> parkingSpots = repository.partialSearchByStreetName(name);
		return new ResponseEntity<List<ParkingSpot>>(parkingSpots, null, HttpStatus.OK);
	}
	
	@GetMapping(path = "/partialSearch/postalCode/{code}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> partialSearchByPostalCode(@PathVariable String code) {
		List<ParkingSpot> parkingSpots = repository.partialSearchByPostalCode(code);
		return new ResponseEntity<List<ParkingSpot>>(parkingSpots, null, HttpStatus.OK);
	}
	
	@GetMapping(path = "/partialSearch/underPrice/{price}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> partialSearchByPrice(@PathVariable float price) {
		List<ParkingSpot> parkingSpots = repository.partialSearchByUnderPrice(price);
		return new ResponseEntity<List<ParkingSpot>>(parkingSpots, null, HttpStatus.OK);
	}
	
	@GetMapping(path = "/partialSearch/overRating/{rating}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> partialSearchbyOverRating(@PathVariable float rating) {
		List<ParkingSpot> parkingSpots = repository.partialSearchByOverRating(rating);
		return new ResponseEntity<List<ParkingSpot>>(parkingSpots, null, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getFreeSpots", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> getFreeSpots(@RequestParam Date startDate, @RequestParam Date endDate){
		List<ParkingSpot> freeSpots = repository.getBetweenTime(startDate, endDate);
		return new ResponseEntity<List<ParkingSpot>>(freeSpots, null, HttpStatus.OK);
	}
	

}
