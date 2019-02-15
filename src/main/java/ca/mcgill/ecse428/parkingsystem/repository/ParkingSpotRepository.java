package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.mcgill.ecse428.parkingsystem.model.Admin;
import ca.mcgill.ecse428.parkingsystem.model.ParkingSpot;

@Repository
public class ParkingSpotRepository {
	
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public ParkingSpot addParkingSpot(ParkingSpot ps) {
		entityManager.persist(ps);
		return ps;
	}
	
	@Transactional
	public ParkingSpot getParkingSpot(String id)
	{
		ParkingSpot foundParkingSpot = entityManager.find(ParkingSpot.class, id);
		return foundParkingSpot;
	}

	@Transactional
	public List<ParkingSpot> getParkingSpots(){
		List<ParkingSpot> ParkingSpots = new ArrayList<ParkingSpot>(); 
		ParkingSpots = entityManager.createQuery("SELECT a FROM ParkingSpot a").getResultList();
		return ParkingSpots;
	}
}
