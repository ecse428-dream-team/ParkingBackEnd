package ca.mcgill.ecse428.parkingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.parkingsystem.model.Reservation;
import ca.mcgill.ecse428.parkingsystem.model.Review;
import ca.mcgill.ecse428.parkingsystem.repository.ReviewRepository;

@RestController
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	ReviewRepository repository;
	
    @GetMapping
    public String hello() {
        return "hello";
    }
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Review addReview(@RequestBody Review rvw) {
	    return repository.addReview(rvw);
	}
	

}
