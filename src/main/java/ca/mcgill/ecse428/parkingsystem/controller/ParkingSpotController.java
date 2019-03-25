package ca.mcgill.ecse428.parkingsystem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import ca.mcgill.ecse428.parkingsystem.model.User;
import ca.mcgill.ecse428.parkingsystem.repository.ParkingSpotRepository;

/**
 * Contains endpoints for the ParkingSpot class
 *
 */
@RestController
@RequestMapping("/spot")
public class ParkingSpotController {

	@Autowired
	ParkingSpotRepository repository;

	/**
	 * Calls method to get a specific parking spot using its id. 
	 * 
	 * @param id id of the looked for parking spot
	 * @return ResponseEntity<ParkingSpot> Returns the parking spot with corresponding id, if exists
	 */
	@GetMapping(path = "/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ParkingSpot> getParkingSpot(@PathVariable int id) {
		ParkingSpot parkingSpotFound = repository.getParkingSpot(id);
		return new ResponseEntity<ParkingSpot>(parkingSpotFound, null, HttpStatus.OK);
	}

	/**
	 * Calls method to retrieve all parking spots that exist in the database.
	 * 
	 * @return ResponseEntity<List<ParkingSpot>> Returns a list of all the parking spots. 
	 */
	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> getParkingSpots() {
		List<ParkingSpot> parkingSpots = repository.getParkingSpots();
		return new ResponseEntity<List<ParkingSpot>>(parkingSpots, null, HttpStatus.OK);
	}

	/**
	 * Calls method to create and add a parking spot to the database
	 * 
	 * @param ps Parking spot to add to the database
	 * @return ParkingSpot Returns Parking spot that has been created
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ParkingSpot addParkingSpot(@RequestBody ParkingSpot ps) {
		return repository.addParkingSpot(ps);
	}

	/**
	 * Calls a method to do search for all parking spots that have an id containing the @param
	 * 
	 * @param id Id that the looked for Parking spots partially have
	 * @return ResponseEntity<List<ParkingSpot>> List of all Parking spot having and id that contains @param
	 */
	@GetMapping(path = "/partialSearch/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> partialSearchById(@PathVariable String id) {
		List<ParkingSpot> parkingSpots = repository.partialSearchById(id);
		return new ResponseEntity<List<ParkingSpot>>(parkingSpots, null, HttpStatus.OK);
	}

	/**
	 * Calls a method to search for all parking spots that have a street name containing @param
	 * 
	 * @param name Street name that the looked for Parking Spots partially have 
	 * @return ResponseEntity<List<ParkingSpot>> Returns a list all Parking Spot having a street name containing @param
	 */
	@GetMapping(path = "/partialSearch/streetName/{name}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> partialSearchByStreetName(@PathVariable String name) {
		List<ParkingSpot> parkingSpots = repository.partialSearchByStreetName(name);
		return new ResponseEntity<List<ParkingSpot>>(parkingSpots, null, HttpStatus.OK);
	}

	/**
	 * Calls a method to search for all parking spots that have a postal code containing @param
	 * 
	 * @param code Postal code that the looked for Parking Spots partially have
	 * @return ResponseEntity<List<ParkingSpot>> Returns a list of Parking Spot having a postal code containing @param
	 */
	@GetMapping(path = "/partialSearch/postalCode/{code}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> partialSearchByPostalCode(@PathVariable String code) {
		List<ParkingSpot> parkingSpots = repository.partialSearchByPostalCode(code);
		return new ResponseEntity<List<ParkingSpot>>(parkingSpots, null, HttpStatus.OK);
	}

	/**
	 * Calls a method to search for all parking spots that have a price under @param
	 * 
	 * @param price Price that the looked for Parking Spots are under
	 * @return ResponseEntity<List<ParkingSpot>> Return a list of Parking Spots under the price @param
	 */
	@GetMapping(path = "/partialSearch/underPrice/{price}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> partialSearchByPrice(@PathVariable float price) {
		List<ParkingSpot> parkingSpots = repository.partialSearchByUnderPrice(price);
		return new ResponseEntity<List<ParkingSpot>>(parkingSpots, null, HttpStatus.OK);
	}

	/**
	 * Calls a method to search for all parking spots that have a certain rating
	 * 
	 * @param rating Rating that the looked for parking spots have
	 * @return ResponseEntity<List<ParkingSpot>> Return a list of Parking Spots that have a certain rating
	 */
	@GetMapping(path = "/partialSearch/overRating/{rating}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> partialSearchbyOverRating(@PathVariable float rating) {
		List<ParkingSpot> parkingSpots = repository.partialSearchByOverRating(rating);
		return new ResponseEntity<List<ParkingSpot>>(parkingSpots, null, HttpStatus.OK);
	}

	/**
	 * Calls a method to look for Parking Spots that do not have any reservations between a certain start date and end date
	 * 
	 * @param startDate Start date of the time frame the Parking Spot should be free of reservations
	 * @param endDate End date of the time frame the Parking Spot should be free of reservations  
	 * @return ResponseEntity<List<ParkingSpot>> Returns a list of Parking Spots that are free during the desired time frame
	 */
	@GetMapping(path = "/getFreeSpots", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ParkingSpot>> getFreeSpots(
			@RequestParam(name = "startDate") String startDate,
			@RequestParam(name = "endDate") String endDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sDate = null;
		try {
			sDate = format.parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date eDate = null;
		try {
			eDate = format.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<ParkingSpot> freeSpots = repository.getBetweenTime(sDate, eDate);
		return new ResponseEntity<List<ParkingSpot>>(freeSpots, null, HttpStatus.OK);
	}

	/**
	 * Calls a method to get the user of a Parking Spot
	 * 
	 * @param id Id of the Parking Spot owned by the looked for User
	 * @return ResponseEntity<User> Returns the user who owns the Parking Spot 
	 */
    @GetMapping(path = "/getOwner/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<User> getOwnerFromId(@PathVariable int id) {
        User owner = repository.getOwnerFromId(id);
        return new ResponseEntity<User>(owner, null, HttpStatus.OK);
    }

}
