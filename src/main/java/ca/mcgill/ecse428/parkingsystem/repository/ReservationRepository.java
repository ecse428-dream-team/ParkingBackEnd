package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.Admin;
import ca.mcgill.ecse428.parkingsystem.model.ParkingSpot;
import ca.mcgill.ecse428.parkingsystem.model.Reservation;

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
	
	// Delete method(s)
	
	@Transactional
	public void deleteRes(Reservation entity) {
		entityManager.remove(entity);
	}
	
	// End Delete method(s)
	
}
