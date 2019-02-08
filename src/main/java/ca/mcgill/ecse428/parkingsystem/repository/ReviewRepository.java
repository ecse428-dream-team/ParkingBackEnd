package ca.mcgill.ecse428.parkingsystem.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.Review;

@Repository
public class ReviewRepository {
	
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public Review addReview(Review review) {
		entityManager.persist(review);
		return review;
	}


}
