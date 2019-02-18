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
		ParkingSpots = entityManager.createQuery("SELECT a FROM ParkingSpot a", ParkingSpot.class).getResultList();
		return ParkingSpots;
	}
	
	
	
	// All methods below here pertains to the story "A renter should be able to browse available parking spots"
	@Transactional
	public List<ParkingSpot> generalSearch() {
		List<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();
		parkingSpots = entityManager.createQuery("SELECT e FROM ParkingSpot e", ParkingSpot.class).getResultList();
		return parkingSpots;
	}
	
	@Transactional
	public List<ParkingSpot> advancedStringFieldSearch(String field, String value) {
		
		List<ParkingSpot> parkingSpots;
		switch(field) {
			case "pKey":
				parkingSpots = entityManager.createQuery("SELECT e FROM Participant e WHERE e.pKey LIKE CONCAT('%', :value, '%')", ParkingSpot.class).getResultList();
				return parkingSpots;
			case "steet_Name":
				parkingSpots = entityManager.createQuery("SELECT e FROM Participant e WHERE e.steet_Name LIKE CONCAT('%', :value, '%')", ParkingSpot.class).getResultList();
				return parkingSpots;
			case "postal_Code":
				parkingSpots = entityManager.createQuery("SELECT e FROM Participant e WHERE e.postal_Code LIKE CONCAT('%', :value, '%')", ParkingSpot.class).getResultList();
				return parkingSpots;
		}
		return null;
	}
	
	@Transactional
	public List<ParkingSpot> advancedFloatFieldSearch(String field, float value) {
		
		List<ParkingSpot> parkingSpots;
		switch(field) {
			case "avg_Rating":
				parkingSpots = entityManager.createQuery("SELECT e FROM Participant e WHERE e.avag_Rating LIKE CONCAT('%', :value, '%')", ParkingSpot.class).getResultList();
				return parkingSpots;
			case "current_Price":
				parkingSpots = entityManager.createQuery("SELECT e FROM Participant e WHERE e.current_Price LIKE CONCAT('%', :value, '%')", ParkingSpot.class).getResultList();
				return parkingSpots;
		}
		return null;
	}
	
	@Transactional
	public List<ParkingSpot> advancedSearchByStreetNum(int value) {
		
		List<ParkingSpot> parkingSpots;
		parkingSpots = entityManager.createQuery("SELECT e FROM Participant e WHERE e.street_Number LIKE CONCAT('%', :value, '%')", ParkingSpot.class).getResultList();
		return parkingSpots;
	}
}
