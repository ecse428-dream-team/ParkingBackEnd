package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.Review;

/**
 * Contains repository methods for the Review class. 
 *
 */
@Repository
public class ReviewRepository {
	
	@Autowired
	EntityManager entityManager;
	
	/**
	 * Create and add a review to the database. 
	 * 
	 * @param review Review to be created and added to the database.
	 * @return Returns the added review. 
	 */
	@Transactional
	public Review addReview(Review review) {
		entityManager.persist(review);
		return review;
	}

	/**
	 * Get the Review with the id given as param. 
	 * 
	 * @param id Id of the desired Review. 
	 * @return Returns the Review with id given as param. 
	 */
	@Transactional
	public Review getReview(int id)
	{
		Review foundReview = entityManager.find(Review.class, id);
		return foundReview;
	}

	/**
	 * Get all Reviews that exist in the database. 
	 * 
	 * @return Returns a list of all Reviews that exist in the database. 
	 */
	@Transactional
	public List<Review> getReviews(){
		List<Review> Reviews = new ArrayList<Review>(); 
		Reviews = entityManager.createQuery("SELECT a FROM Review a").getResultList();
		return Reviews;
	}
	
}
