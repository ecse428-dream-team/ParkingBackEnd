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


@Repository
public class UserRepository {
	
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public User addUser(User user) {
		entityManager.persist(user);
		return user;
	}

	@Transactional
    public List<User> getUserByFirstName(String firstName) {
	    List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.first_name LIKE :custName", User.class)
                .setParameter("custName", firstName)
                .setMaxResults(1)
                .getResultList();

        if (users != null) return users;
        return null;
    }

    @Transactional
    public List<User> getUserByLastName(String lastName) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.last_Name LIKE :custName", User.class)
                .setParameter("custName", lastName)
                .setMaxResults(1)
                .getResultList();

        if (users != null) return users;
        return null;
    }

    @Transactional
    public User getUserById(String id) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.userID LIKE :custId", User.class)
                .setParameter("custId", id)
                .setMaxResults(1)
                .getResultList();

        if (users != null && users.size()>=1) return (User)users.get(0);
        return null;

    }
    
    @Transactional
    public User getUserByEmail(String email) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.email LIKE :custEmail", User.class)
                .setParameter("custEmail", email)
                .setMaxResults(1)
                .getResultList();

        if (users != null) return (User)users.get(0);
        return null;

    }


	@Transactional
	public List<User> getAllUsers(){
		List<User> users = entityManager.createQuery("SELECT a FROM User a", User.class)
                .getResultList();

        if (users != null) return users;
        return null;
	}

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
