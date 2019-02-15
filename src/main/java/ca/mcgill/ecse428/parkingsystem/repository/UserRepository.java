package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.Admin;
import ca.mcgill.ecse428.parkingsystem.model.User;

import java.util.List;

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
	    List users = entityManager.createQuery("SELECT u FROM User u WHERE u.first_name LIKE :custName", User.class)
                .setParameter("custName", firstName)
                .setMaxResults(1)
                .getResultList();

	    return users;
    }

    @Transactional
    public List<User> getUserByLastName(String lastName) {
        List users = entityManager.createQuery("SELECT u FROM User u WHERE u.last_Name LIKE :custName", User.class)
                .setParameter("custName", lastName)
                .setMaxResults(1)
                .getResultList();

        return users;
    }

    @Transactional
    public List<User> getUserById(String id) {
        List users = entityManager.createQuery("SELECT u FROM User u WHERE u.userID LIKE :custId", User.class)
                .setParameter("custId", id)
                .setMaxResults(1)
                .getResultList();

        return users;
    }


	@Transactional
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<User>(); 
		users = entityManager.createQuery("SELECT a FROM User a").getResultList();
		return users;
	}

}
