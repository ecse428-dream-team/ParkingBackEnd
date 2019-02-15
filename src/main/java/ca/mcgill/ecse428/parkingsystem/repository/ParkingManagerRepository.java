package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.Admin;
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
	
	@Transactional
	public ParkingManager getParkingManager(String id)
	{
		ParkingManager foundParkingManager = entityManager.find(ParkingManager.class, id);
		return foundParkingManager;
	}

	@Transactional
	public List<ParkingManager> getParkingManagers(){
		List<ParkingManager> ParkingManagers = new ArrayList<ParkingManager>(); 
		ParkingManagers = entityManager.createQuery("SELECT a FROM ParkingManager a").getResultList();
		return ParkingManagers;
	}
}
