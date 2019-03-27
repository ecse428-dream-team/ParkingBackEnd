package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.ParkingSpot;
import ca.mcgill.ecse428.parkingsystem.model.Reservation;
import ca.mcgill.ecse428.parkingsystem.model.User;

/**
 * Contains repository methods for the ParkingSpot class.
 *
 */

@Repository
public class ParkingSpotRepository {

	@Autowired
	EntityManager entityManager;

	/**
	 * Add and create a Parking Spot to the database.
	 * 
	 * @param ps
	 *            Parking Spot to be created and added to the database.
	 * @return Returns the created Parking Spot.
	 */
	@Transactional
	public ParkingSpot addParkingSpot(ParkingSpot ps) {
		entityManager.persist(ps);
		return ps;
	}

	/**
	 * Get a Parking Spot given its id.
	 * 
	 * @param id
	 *            Id of the desired Parking Spot.
	 * @return Returns the parking spot with the provided id.
	 */
	@Transactional
	public ParkingSpot getParkingSpot(int id) {
		ParkingSpot foundParkingSpot = entityManager.find(ParkingSpot.class, id);
		return foundParkingSpot;
	}

	/**
	 * Get all existing Parking Spots from the database.
	 * 
	 * @return Returns a list of all Parking Spots.
	 */
	@Transactional
	public List<ParkingSpot> getParkingSpots() {
		List<ParkingSpot> ParkingSpots = new ArrayList<ParkingSpot>();
		ParkingSpots = entityManager.createQuery("SELECT a FROM ParkingSpot a", ParkingSpot.class).getResultList();
		return ParkingSpots;
	}

	/**
	 * Get all Parking Spots with an id containing the provided argument.
	 * 
	 * @param id
	 *            Id to be looked for.
	 * @return Returns a list of Parking Spots with id containing the id provided as
	 *         argument.
	 */
	@Transactional
	public List<ParkingSpot> partialSearchById(String id) {

		List<ParkingSpot> parkingSpots;
		parkingSpots = entityManager
				.createQuery("SELECT e FROM ParkingSpot e WHERE e.pKey LIKE CONCAT('%', :custId, '%')",
						ParkingSpot.class)
				.setParameter("custId", id).getResultList();
		return parkingSpots;
	}

	/**
	 * Get all Parking Spots with a street name containing the provided argument.
	 * 
	 * @param name
	 *            String the desired Parking Spots' street name contain
	 * @return Returns a list of all Parking Spot with street name containing the
	 *         provided String.
	 */
	@Transactional
	public List<ParkingSpot> partialSearchByStreetName(String name) {

		List<ParkingSpot> parkingSpots;
		parkingSpots = entityManager
				.createQuery("SELECT e FROM ParkingSpot e WHERE e.street_Name LIKE CONCAT('%', :custName, '%')",
						ParkingSpot.class)
				.setParameter("custName", name).getResultList();
		return parkingSpots;
	}

	/**
	 * Get all Parking Spots with a postal code containing the provided argument.
	 * 
	 * @param code
	 *            String the desired Parking Spots' postal code contain.
	 * @return Returns a list of all Parking Spots with a postal code containing the
	 *         provided String.
	 */
	@Transactional
	public List<ParkingSpot> partialSearchByPostalCode(String code) {

		List<ParkingSpot> parkingSpots;
		parkingSpots = entityManager
				.createQuery("SELECT e FROM ParkingSpot e WHERE e.postal_Code LIKE CONCAT('%', :custCode, '%')",
						ParkingSpot.class)
				.setParameter("custCode", code).getResultList();
		return parkingSpots;
	}

	/**
	 * Get all Parking Spots with a price lower then the price given as a param.
	 * 
	 * @param price
	 *            Price the desired Parking Spots are under.
	 * @return Returns a list of all Parking Spots with a price lower the the price
	 *         given as param.
	 */
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

	/**
	 * Get all Parking Spots with a price over then the price given as a param.
	 * 
	 * @param price
	 *            Price the desired Parking Spots are over.
	 * @return Returns a list of all Parking Spots with a price over the the price
	 *         given as param.
	 */
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

	/**
	 * Get all Parking Spots with a street number containing the int given as param.
	 * 
	 * @param value
	 *            Street number the desired Parking Spots' street number contain.
	 * @return Returns a list of all Parking Spots containing the int given as
	 *         param.
	 */
	@Transactional
	public List<ParkingSpot> advancedSearchByStreetNum(int value) {

		List<ParkingSpot> parkingSpots;
		parkingSpots = entityManager
				.createQuery("SELECT e FROM ParkingSpot e WHERE e.street_Number LIKE CONCAT('%', :value, '%')",
						ParkingSpot.class)
				.getResultList();
		return parkingSpots;
	}

	/**
	 * Get a list of all Parking Spots which have no reservations between the start
	 * and end time given as param.
	 * 
	 * @param start
	 *            Start time of the time frame with no reservation.
	 * @param end
	 *            End time of the time frame with no reservation.
	 * @return Returns a list of all Parking Spots which have no reservations
	 *         between the start and end time given as param.
	 */
	@Transactional
	public List<ParkingSpot> getBetweenTime(Date start, Date end) {
		List<ParkingSpot> ParkingSpots = new ArrayList<ParkingSpot>();
		ParkingSpots = entityManager.createQuery("SELECT a FROM ParkingSpot a", ParkingSpot.class).getResultList();
		List<ParkingSpot> goodSpots = new ArrayList<ParkingSpot>();

		for (int i = 0; i < ParkingSpots.size(); i++) {
			boolean isBad = false;
			ParkingSpot current = ParkingSpots.get(i);
			List<Reservation> reservations = current.getReservations();

			for (int j = 0; j < reservations.size(); j++) {
				Reservation currentReservation = reservations.get(j);
				if (currentReservation.getStart_Date().before(end) && currentReservation.getEnd_Date().after(start)) {
					isBad = true;
					break;
				}
			}

			if (isBad == false) {
				goodSpots.add(current);
			}
		}

		return goodSpots;
	}

	/**
	 * Get the User who owns the Parking Spot with id given as param. 
	 * 
	 * @param id Id of the Parking Spot of which the owning User is desired. 
	 * @return Returns the User who owns the Parking Spot with the id provide das param. 
	 */
	@Transactional
	public User getOwnerFromId(int id) {
		ParkingSpot spot = entityManager.find(ParkingSpot.class, id);
		User owner = spot.getUser();
		return owner;
	}

}
