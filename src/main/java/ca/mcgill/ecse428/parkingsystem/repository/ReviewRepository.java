package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.Admin;
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

	@Transactional
	public Review getReview(String id)
	{
		Review foundReview = entityManager.find(Review.class, id);
		return foundReview;
	}

	@Transactional
	public List<Review> getReviews(){
		List<Review> Reviews = new ArrayList<Review>(); 
		Reviews = entityManager.createQuery("SELECT a FROM Review a").getResultList();
		return Reviews;
	}
	
}
