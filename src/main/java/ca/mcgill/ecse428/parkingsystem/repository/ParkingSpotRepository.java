package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.ParkingSpot;
import ca.mcgill.ecse428.parkingsystem.model.User;

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
    public List<ParkingSpot> getSpotByStreetNumber(int number) {
	    TypedQuery<ParkingSpot> query1 = entityManager
	    .createQuery("SELECT s FROM ParkingSpot s WHERE s.street_Number = :Streetnumber", ParkingSpot.class);
        query1.setParameter("Streetnumber", number);
	    List<ParkingSpot> spots = query1.getResultList();
	    return spots;
    }
	
	
	
	

}
