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

import ca.mcgill.ecse428.parkingsystem.model.Review;
import ca.mcgill.ecse428.parkingsystem.repository.ReviewRepository;

/**
 * Contains endpoints for the Review class.
 *
 */

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewRepository repository;

	/**
	 * Calls a method to get the review with specific id. 
	 * 
	 * @param id Id of the looked for review.
	 * @return ResponseEntity<Review> Returns the review with specific id. 
	 */
	@GetMapping(path = "/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Review> getReview(@PathVariable int id) {

		Review ReviewFound = repository.getReview(id);
		return new ResponseEntity<Review>(ReviewFound, null, HttpStatus.OK);

	}

	/**
	 * Calls a method to get all reviews in the database. 
	 * 
	 * @return ResponseEntity<List<Review>> Returns a list of all the reviews in the database. 
	 */
	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Review>> getReviews() {
		List<Review> Reviews = repository.getReviews();
		return new ResponseEntity<List<Review>>(Reviews, null, HttpStatus.OK);
	}

	/**
	 * Calls a method to create and add a review to the database.
	 * 
	 * @param rvw Review to be added to the database. 
	 * @return ResponseEntity<Review> Returns the review that has been created and added to the database. 
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Review addReview(@RequestBody Review rvw) {
		return repository.addReview(rvw);
	}

}
