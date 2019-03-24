package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ca.mcgill.ecse428.parkingsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.mcgill.ecse428.parkingsystem.model.Admin;
import ca.mcgill.ecse428.parkingsystem.model.ParkingSpot;
import ca.mcgill.ecse428.parkingsystem.model.Reservation;

@Repository
public class ParkingSpotRepository {

	@Autowired
	EntityManager entityManager;

	@Transactional
	public ParkingSpot addParkingSpot(ParkingSpot ps) {
		entityManager.persist(ps);
		return ps;
	}

	// All methods below here pertains to the story "A renter should be able to
	// browse available parking spots"
	@Transactional
	public ParkingSpot getParkingSpot(int id) {
		ParkingSpot foundParkingSpot = entityManager.find(ParkingSpot.class, id);
		return foundParkingSpot;
	}

	@Transactional
	public List<ParkingSpot> getParkingSpots() {
		List<ParkingSpot> ParkingSpots = new ArrayList<ParkingSpot>();
		ParkingSpots = entityManager.createQuery("SELECT a FROM ParkingSpot a", ParkingSpot.class).getResultList();
		return ParkingSpots;
	}

	@Transactional
	public List<ParkingSpot> partialSearchById(String id) {

		List<ParkingSpot> parkingSpots;
		parkingSpots = entityManager
				.createQuery("SELECT e FROM ParkingSpot e WHERE e.pKey LIKE CONCAT('%', :custId, '%')", ParkingSpot.class)
				.setParameter("custId", id).getResultList();
		return parkingSpots;
	}

	@Transactional
	public List<ParkingSpot> partialSearchByStreetName(String name) {

		List<ParkingSpot> parkingSpots;
		parkingSpots = entityManager
				.createQuery("SELECT e FROM ParkingSpot e WHERE e.street_Name LIKE CONCAT('%', :custName, '%')", ParkingSpot.class)
				.setParameter("custName", name).getResultList();
		return parkingSpots;
	}

	@Transactional
	public List<ParkingSpot> partialSearchByPostalCode(String code) {

		List<ParkingSpot> parkingSpots;
		parkingSpots = entityManager
				.createQuery("SELECT e FROM ParkingSpot e WHERE e.postal_Code LIKE CONCAT('%', :custCode, '%')",
						ParkingSpot.class)
				.setParameter("custCode", code).getResultList();
		return parkingSpots;
	}

	@Transactional
	public List<ParkingSpot> partialSearchByUnderPrice(float price) {

		List<ParkingSpot> parkingSpots;
		parkingSpots = entityManager.createQuery("SELECT e FROM ParkingSpot e", ParkingSpot.class).getResultList();

		List<ParkingSpot> badSpots = new ArrayList<ParkingSpot>();
		for (int i = 0; i < parkingSpots.size(); i++) {
			if (parkingSpots.get(i).getCurrent_Price() > price) {
				badSpots.add(parkingSpots.get(i));
			}
		}
		parkingSpots.removeAll(badSpots);
		return parkingSpots;
	}

	@Transactional
	public List<ParkingSpot> partialSearchByOverRating(float rating) {

		List<ParkingSpot> parkingSpots;
		parkingSpots = entityManager.createQuery("SELECT e FROM ParkingSpot e", ParkingSpot.class).getResultList();

		List<ParkingSpot> badSpots = new ArrayList<ParkingSpot>();
		for (int i = 0; i < parkingSpots.size(); i++) {
			if (parkingSpots.get(i).getAvg_Rating() < rating) {
				badSpots.add(parkingSpots.get(i));
			}
		}
		parkingSpots.removeAll(badSpots);
		return parkingSpots;
	}

	@Transactional
	public List<ParkingSpot> advancedSearchByStreetNum(int value) {

		List<ParkingSpot> parkingSpots;
		parkingSpots = entityManager
				.createQuery("SELECT e FROM ParkingSpot e WHERE e.street_Number LIKE CONCAT('%', :value, '%')",
						ParkingSpot.class)
				.getResultList();
		return parkingSpots;
	}

	@Transactional
	public List<ParkingSpot> getBetweenTime(Date start, Date end) {
		List<ParkingSpot> ParkingSpots = new ArrayList<ParkingSpot>();
		ParkingSpots = entityManager.createQuery("SELECT a FROM ParkingSpot a", ParkingSpot.class).getResultList();
		List<ParkingSpot> goodSpots = new ArrayList<ParkingSpot>();
		
		for(int i = 0; i < ParkingSpots.size(); i++){
			boolean isBad = false;
			ParkingSpot current = ParkingSpots.get(i);
			List<Reservation> reservations = current.getReservations();
		
			for(int j = 0; j < reservations.size(); j++) 
			{
				Reservation currentReservation = reservations.get(j);
				if(currentReservation.getStart_Date().before(end) && currentReservation.getEnd_Date().after(start)) 
				{
					isBad = true;
					break;
				}
			}
			
			if(isBad == false) {
				goodSpots.add(current);
			}
		}
		
		return goodSpots;
	}

	@Transactional
    public User getOwnerFromId(int id) {
        ParkingSpot spot = entityManager.find(ParkingSpot.class, id);
        User owner = spot.getUser();
        return owner;
    }
	
}
