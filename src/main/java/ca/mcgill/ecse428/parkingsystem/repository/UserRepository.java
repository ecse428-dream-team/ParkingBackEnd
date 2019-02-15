package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.Admin;
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
	public User getUser(String id)
	{
		User foundUser = entityManager.find(User.class, id);
		return foundUser;
	}

	@Transactional
	public List<User> getUsers(){
		List<User> users = new ArrayList<User>(); 
		users = entityManager.createQuery("SELECT a FROM User a").getResultList();
		return users;
	}
}
