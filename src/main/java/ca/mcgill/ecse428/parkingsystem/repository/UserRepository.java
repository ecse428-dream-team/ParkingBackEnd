package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

	// Edit user method
	
	@Transactional
	public User editUser(User oldUser, User newUser) {
		oldUser.setEmail(newUser.getEmail());
		oldUser.setfirst_name(newUser.getfirst_name());
		oldUser.setLast_Name(newUser.getLast_Name());
		oldUser.setPassword(newUser.getPassword());
		return oldUser;
	}
	
	// End edit user method
	
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
        List users = entityManager.createQuery("SELECT u FROM User u WHERE u.userID LIKE :custId", User.class)
                .setParameter("custId", id)
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
	
	// Delete method(s)
	
	@Transactional
	public void deleteUser(User entity) {
		entityManager.remove(entity);
	}
	
	// End Delete method(s)

}
