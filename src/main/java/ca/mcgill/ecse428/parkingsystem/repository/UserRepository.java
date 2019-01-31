package ca.mcgill.ecse428.parkingsystem.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	
	@Autowired
	EntityManager entityManager;

}
