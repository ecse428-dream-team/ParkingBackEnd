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

import ca.mcgill.ecse428.parkingsystem.model.Admin;
import ca.mcgill.ecse428.parkingsystem.model.Reservation;
import ca.mcgill.ecse428.parkingsystem.model.Review;
import ca.mcgill.ecse428.parkingsystem.repository.ReviewRepository;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewRepository repository;

	@GetMapping(path = "/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Review> getReview(@PathVariable String id) {

		Review ReviewFound = repository.getReview(id);
		return new ResponseEntity<Review>(ReviewFound, null, HttpStatus.OK);

	}

	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Review>> getReviews() {
		List<Review> Reviews = repository.getReviews();
		return new ResponseEntity<List<Review>>(Reviews, null, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public Review addReview(@RequestBody Review rvw) {
		return repository.addReview(rvw);
	}

}
