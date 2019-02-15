package ca.mcgill.ecse428.parkingsystem.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
