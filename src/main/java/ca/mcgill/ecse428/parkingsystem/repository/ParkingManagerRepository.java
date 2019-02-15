package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ca.mcgill.ecse428.parkingsystem.model.User;
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
	public ParkingManager getParkingManager(String pKey)
	{
        List<ParkingManager> parkingManager = entityManager.createQuery("SELECT p FROM ParkingManager p WHERE p.PKey LIKE :parkingManagerKey", ParkingManager.class)
                .setParameter("parkingManagerKey", pKey)
                .setMaxResults(1)
                .getResultList();

		return parkingManager.get(0);
	}

	@Transactional
	public List<ParkingManager> getParkingManagers(){
        List<ParkingManager> parkingManagers = entityManager.createQuery("SELECT p FROM ParkingManager p", ParkingManager.class)
                .getResultList();

		return parkingManagers;
	}
}
