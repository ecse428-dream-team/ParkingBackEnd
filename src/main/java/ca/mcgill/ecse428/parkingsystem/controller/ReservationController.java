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

import ca.mcgill.ecse428.parkingsystem.model.Reservation;
import ca.mcgill.ecse428.parkingsystem.repository.ReservationRepository;

/**
 * Contains endpoints of the Reservation class
 *
 */
@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	ReservationRepository repository;

	/**
	 * Calls a method to get the reservation with the corresponding id
	 * 
	 * @param id
	 *            Id of the looked for Reservation
	 * @return ResponseEntity<Reservation> Returns the reservation corresponding to
	 *         the id
	 */
	@GetMapping(path = "/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Reservation> getReservation(@PathVariable int id) {

		Reservation ReservationFound = repository.getReservation(id);
		return new ResponseEntity<>(ReservationFound, null, HttpStatus.OK);
	}

	/**
	 * Calls a method to get all Reservations that exist in the database
	 * 
	 * @return ResponseEntity<List<Reservation> Returns a list of all reservations
	 *         that exist in the database
	 */
	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Reservation>> getReservations() {
		List<Reservation> Reservations = repository.getReservations();
		return new ResponseEntity<>(Reservations, null, HttpStatus.OK);
	}

	/**
	 *Calls a method to get all Reservations that a user's spots have. For example, a user owns 2
	 * spots with 3 reservations each. This method retrieves all 6 reservations.
	 * 
	 * @param id Id of the user that owns the spots of the reservations
	 * @return ResponseEntity<List<Reservation> Returns a list of reservations linked to the spots owned by the user
	 */
	@GetMapping(path = "/fromUserSpot/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Reservation>> getReservationFromUserSpot(@PathVariable String id) {
		List<Reservation> Reservations = repository.getReservationFromUserSpot(id);
		return new ResponseEntity<>(Reservations, null, HttpStatus.OK);
	}

	/**
	 *Calls a method to get all the reservations the user has. In a more realistic sense, all the reservations the user had made. 
	 * 
	 * @param id Id of the user who has made the reservations. 
	 * @return ResponseEntity<List<Reservation> Returns a list of reservations associated to the user
	 */
	@GetMapping(path = "/forUser/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Reservation>> getReservationsForUser(@PathVariable String id) {
		List<Reservation> Reservations = repository.getReservationForUser(id);
		return new ResponseEntity<>(Reservations, null, HttpStatus.OK);
	}

	/**
	 *Calls a method to create and add a reservation to the database
	 * 
	 * @param rsv Reservation that will be created and added to the database 
	 * @return
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Reservation addReservation(@RequestBody Reservation rsv) {
		return repository.addReservation(rsv);
	}

	/**
	 *Calls a method to delete the reservation with corresponding pkey
	 * 
	 * @param pKey Pkey of the reservation that needs to be deleted
	 * @return ResponseEntity<String> Confirmation of the deletion. 
	 */
	@PostMapping(path = "/delete/{pKey}")
	public ResponseEntity<String> deleteReservation(@PathVariable int pKey) {
		boolean isDeleted = repository.deleteReservation(pKey);
		if (isDeleted) {
			return new ResponseEntity<String>("Reservation successfully deleted.", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Reservation could not be found.", HttpStatus.BAD_REQUEST);
		}
	}

}
