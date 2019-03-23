package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.ParkingManager;
import ca.mcgill.ecse428.parkingsystem.model.ParkingSpot;
import ca.mcgill.ecse428.parkingsystem.model.Reservation;
import ca.mcgill.ecse428.parkingsystem.model.User;

@Repository
public class ReservationRepository {
	
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public Reservation addReservation(Reservation rsv) {
		entityManager.persist(rsv);
		return rsv;
	}

	@Transactional
	public Reservation getReservation(String id)
	{
		Reservation foundReservation = entityManager.find(Reservation.class, id);
		return foundReservation;
	}

	@Transactional
	public List<Reservation> getReservations(){
		List<Reservation> Reservations = new ArrayList<Reservation>(); 
		Reservations = entityManager.createQuery("SELECT a FROM Reservation a").getResultList();
		return Reservations;
	}
	
	// All methods below pertains to the "A seller can cancel a reservation" story
	// 	1) Add functionality to let user cancel their the reservation
	//	2) Create the Backend functionality to delete the data from the table
	//	3) Vertify the system so the reservation spot should be available
	
	@Transactional
	public Boolean deleteReservation(int pKey) {
		Boolean isDeleted = false; 
		Reservation retrievedReservation = entityManager.find(Reservation.class, pKey);
		if (retrievedReservation == null) {
			System.out.println("\n" + "The specific reservation could not be found. Can not delete null reservation!" + "\n");
			return isDeleted;
		}
		
		ParkingManager pm = retrievedReservation.getParkingManager();
		ParkingSpot ps = retrievedReservation.getParkingSpot();
		User usr = retrievedReservation.getUser();
		
		pm.removeReservation(retrievedReservation);
		ps.removeReservation(retrievedReservation);
		usr.removeReservation(retrievedReservation);
		
		entityManager.persist(pm);
		entityManager.remove(retrievedReservation);
		
		isDeleted = true;
		return isDeleted;
	}
	
	@Transactional
	public List<Reservation> getReservationFromUserSpot(String id){
		User usr = entityManager.find(User.class, id);
		List<Reservation> reservations = new ArrayList<Reservation>();
		
		if(usr.hasParkingSpots()) {
			for(int i = 0; i < usr.getParkingSpots().size(); i++) {
				ParkingSpot spot = usr.getParkingSpot(i);
				for(int j = 0; j < spot.getReservations().size(); j++) {
					reservations.add(spot.getReservation(j));
				}
			}
		}
		
		return reservations;
	}
	
}
