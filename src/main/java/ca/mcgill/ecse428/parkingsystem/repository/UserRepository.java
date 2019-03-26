package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.ParkingManager;
import ca.mcgill.ecse428.parkingsystem.model.ParkingSpot;
import ca.mcgill.ecse428.parkingsystem.model.Reservation;
import ca.mcgill.ecse428.parkingsystem.model.Review;
import ca.mcgill.ecse428.parkingsystem.model.User;

/**
 * Contains the repository methods for the User class.  
 *
 */

@Repository
public class UserRepository {
	
	@Autowired
	EntityManager entityManager;
	
	/**
	 * Create and add a user to the database. 
	 * 
	 * @param user User to be created and added to the database. 
	 * @return Returns the added User. 
	 */
	@Transactional
	public User addUser(User user) {
		entityManager.persist(user);
		return user;
	}

	/**
	 * Get user with first name corresponding to the string given as param. 
	 * 
	 * @param firstName First name of the desired User. 
	 * @return Returns the User with the first name given as param. 
	 */
	@Transactional
    public List<User> getUserByFirstName(String firstName) {
	    List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.first_name LIKE :custName", User.class)
                .setParameter("custName", firstName)
                .setMaxResults(1)
                .getResultList();

        if (users != null) return users;
        return null;
    }

	/**
	 * Get user with last name corresponding to the string given as param. 
	 * 
	 * @param lastName Last name of the desired User. 
	 * @return Returns the User with the last name given as param. 
	 */
    @Transactional
    public List<User> getUserByLastName(String lastName) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.last_Name LIKE :custName", User.class)
                .setParameter("custName", lastName)
                .setMaxResults(1)
                .getResultList();

        if (users != null) return users;
        return null;
    }

    /**
     * Get user with id corresponding to the id given as param. 
     * 
     * @param id Id of the desired User. 
     * @return Returns the User with Id corresponding to the id given as param. 
     */
    @Transactional
    public User getUserById(String id) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.userID LIKE :custId", User.class)
                .setParameter("custId", id)
                .setMaxResults(1)
                .getResultList();

        if (users != null && users.size()>=1) return (User)users.get(0);
        return null;

    }
    /**
     * Get user with email corresponding to the email given as param. 
     * 
     * @param email Email of the desired User. 
     * @return Returns the User with email corresponding to theemail given as param. 
     */
    @Transactional
    public User getUserByEmail(String email) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.email LIKE :custEmail", User.class)
                .setParameter("custEmail", email)
                .setMaxResults(1)
                .getResultList();

        if (users != null) return (User)users.get(0);
        return null;

    }

    /**
     * Get a list of all Users that exist in the database. 
     * 
     * @return Returns a list of all User that exist in the database. 
     */
	@Transactional
	public List<User> getAllUsers(){
		List<User> users = entityManager.createQuery("SELECT a FROM User a", User.class)
                .getResultList();

        if (users != null) return users;
        return null;
	}

	/**
	 * Delete User with id corresponding to the id given as param. 
	 * 
	 * @param username Id of the user to delete. 
	 * @return Boolean confirming the deletion of the user. 
	 */
	@Transactional
	public boolean deleteUser(String username) {
		User usr = entityManager.find(User.class, username);
		if (usr == null) 
			return false;
		ParkingManager pm = usr.getParkingManager();
		
		if(usr.hasParkingSpots()) {
			List<ParkingSpot> spots = usr.getParkingSpots();
			for(int i = 0; i < spots.size(); i++) {
				ParkingSpot spot = spots.get(i);
				if(spot.hasReservations()) {
					List<Reservation> rsv = usr.getReservations();
					for(int j = 0; j < rsv.size(); j++) {
						Reservation r = rsv.get(j);
						pm.removeReservation(r);
					}
				}
				if(spot.hasReviews()) {
					List<Review> rvws = spot.getReviews();
					for(int j = 0; j < rvws.size(); j++) {
						Review rvw = rvws.get(j);
						pm.removeReview(rvw);
					}
				}
				pm.removeParkingSpot(spot);
			}
		}
		
		if(usr.hasReservations()) {
			List<Reservation> rsv = usr.getReservations();
			for(int i = 0; i < rsv.size(); i++) {
				Reservation r = rsv.get(i);
				pm.removeReservation(r);
			}
		}
		
		pm.removeUser(usr);
		
		entityManager.refresh(pm);
		entityManager.remove(usr);
		return true;
	}
	

}
