package ca.mcgill.ecse428.parkingsystem.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.ParkingManager;

@Repository
public class ParkingManagerRepository {
	
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public ParkingManager addManager(ParkingManager manager) {
		entityManager.persist(manager);
		return manager;
	}

}
