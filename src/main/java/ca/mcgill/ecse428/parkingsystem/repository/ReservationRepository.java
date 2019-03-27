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

/**
 * Contains all repository methods for the Reservation class.
 *
 */

@Repository
public class ReservationRepository {

	@Autowired
	EntityManager entityManager;

	/**
	 * Create and add a reservation to the database.
	 * 
	 * @param rsv
	 *            Reservation to be created and added to the database.
	 * @return Returns the added Reservation .
	 */
	@Transactional
	public Reservation addReservation(Reservation rsv) {
		entityManager.persist(rsv);
		return rsv;
	}

	/**
	 * Get the reservation with the id provided as param.
	 * 
	 * @param id
	 *            Id of the desired Reservation.
	 * @return Returns the Reservation with id given as param.
	 */
	@Transactional
	public Reservation getReservation(int id) {
		Reservation foundReservation = entityManager.find(Reservation.class, id);
		return foundReservation;
	}

	/**
	 * Get all reservations that exist in the database.
	 * 
	 * @return Returns a list of all Reservations that exist in the database.
	 */
	@Transactional
	public List<Reservation> getReservations() {
		List<Reservation> Reservations = new ArrayList<Reservation>();
		Reservations = entityManager.createQuery("SELECT a FROM Reservation a").getResultList();
		return Reservations;
	}

	/**
	 * Delete the Reservation with the primary key given as param.
	 * 
	 * @param pKey
	 *            Primary key of the Reservation to be deleted.
	 * @return Returns a boolean confirming the deletion of the Reservation.
	 */
	@Transactional
	public Boolean deleteReservation(int pKey) {
		Boolean isDeleted = false;
		Reservation retrievedReservation = entityManager.find(Reservation.class, pKey);
		if (retrievedReservation == null) {
			System.out.println(
					"\n" + "The specific reservation could not be found. Can not delete null reservation!" + "\n");
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

	/**
	 * Get all reservations related to the Parking Spots of the User with id given
	 * as param.
	 * 
	 * @param id
	 *            Id of the User of whom the Parking Spots Reservations are desired.
	 * @return Returns a list of all Reservations related to the Parking Spots owned
	 *         by the User with id provided as param.
	 */
	@Transactional
	public List<Reservation> getReservationFromUserSpot(String id) {
		User usr = entityManager.find(User.class, id);
		List<Reservation> reservations = new ArrayList<Reservation>();

		if (usr.hasParkingSpots()) {
			for (int i = 0; i < usr.getParkingSpots().size(); i++) {
				ParkingSpot spot = usr.getParkingSpot(i);
				for (int j = 0; j < spot.getReservations().size(); j++) {
					reservations.add(spot.getReservation(j));
				}
			}
		}

		return reservations;
	}

	/**
	 * Get all reservations a User has made.
	 * 
	 * @param id
	 *            Id of the User who has made the desired Reservations.
	 * @return Returns a list of all Reservations the User with given id as param
	 *         has made.
	 */
	@Transactional
	public List<Reservation> getReservationForUser(String id) {
		User usr = entityManager.find(User.class, id);
		List<Reservation> reservations = new ArrayList<Reservation>();

		if (usr.hasReservations()) {
			for (int i = 0; i < usr.getReservations().size(); i++) {
				Reservation reservation = usr.getReservation(i);
				reservations.add(reservation);
			}
		}

		return reservations;
	}

}
