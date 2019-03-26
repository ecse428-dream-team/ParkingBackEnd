package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.ParkingManager;

/**
 * Contains repository methods for the Parking Manager class. 
 *
 */
@Repository
public class ParkingManagerRepository {
	
	@Autowired
	EntityManager entityManager;
	
	/**
	 * Create and add a Parking Manager to the database. 
	 * 
	 * @param manager Manager to create and add to the database. 
	 * @return Returns the created ParkingManager. 
	 */
	@Transactional
	public ParkingManager addManager(ParkingManager manager) {
		entityManager.persist(manager);
		return manager;
	}
	
	/**
	 * Get a Parking Manager given its primary key. 
	 * 
	 * @param pKey Primary key of the desired Parking Manager. 
	 * @return Returns the Parking Manager corresponding to the provided pkey. 
	 */
	@Transactional
	public ParkingManager getParkingManager(String pKey)
	{
        List<ParkingManager> parkingManager = entityManager.createQuery("SELECT p FROM ParkingManager p WHERE p.PKey LIKE :parkingManagerKey", ParkingManager.class)
                .setParameter("parkingManagerKey", pKey)
                .setMaxResults(1)
                .getResultList();

		return parkingManager.get(0);
	}

	/**
	 * Get all Parking Managers that exist in the database. 
	 * 
	 * @return Returns a list of all ParkingManager that exist in the database. 
	 */
	@Transactional
	public List<ParkingManager> getParkingManagers(){
        List<ParkingManager> parkingManagers = entityManager.createQuery("SELECT p FROM ParkingManager p", ParkingManager.class)
                .getResultList();

		return parkingManagers;
	}

	/**
	 * 
	 * Delete a Parking Manager given its primary key. 
	 * 
	 * @param pkey Primary key of the Parking Manager to be deleted. 
	 */
    @Transactional
    public void deleteManager(String pkey){

	    ParkingManager pm = entityManager.find(ParkingManager.class, pkey);
	    entityManager.remove(pm);

    }
}
