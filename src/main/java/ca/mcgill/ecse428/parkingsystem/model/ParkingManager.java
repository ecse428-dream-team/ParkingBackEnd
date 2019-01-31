/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4403.5bb429550 modeling language!*/

package ca.mcgill.ecse428.parkingsystem.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// line 4 "../../../../../../../../ump/tmp864058/model.ump"
// line 103 "../../../../../../../../ump/tmp864058/model.ump"
@Entity
@Table(name="parkingmanager")
public class ParkingManager {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Manager ID
	private String id;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// ParkingManager Associations
	private List<Person> persons;
	private List<ParkingSpot> parkingSpots;
	private List<Reservation> reservations;
	private List<Review> reviews;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public ParkingManager(String anId) {
		persons = new ArrayList<Person>();
		parkingSpots = new ArrayList<ParkingSpot>();
		reservations = new ArrayList<Reservation>();
		reviews = new ArrayList<Review>();
		id = anId;
	}

	// ------------------------
	// INTERFACE
	// ------------------------
	/* Code from template association_GetMany */
	public Person getPerson(int index) {
		Person aPerson = persons.get(index);
		return aPerson;
	}

	public List<Person> getPersons() {
		List<Person> newPersons = Collections.unmodifiableList(persons);
		return newPersons;
	}

	public int numberOfPersons() {
		int number = persons.size();
		return number;
	}

	public boolean hasPersons() {
		boolean has = persons.size() > 0;
		return has;
	}

	public int indexOfPerson(Person aPerson) {
		int index = persons.indexOf(aPerson);
		return index;
	}

	/* Code from template association_GetMany_clear */
	protected void clear_persons() {
		persons.clear();
	}

	/* Code from template association_GetMany */
	public ParkingSpot getParkingSpot(int index) {
		ParkingSpot aParkingSpot = parkingSpots.get(index);
		return aParkingSpot;
	}

	public List<ParkingSpot> getParkingSpots() {
		List<ParkingSpot> newParkingSpots = Collections.unmodifiableList(parkingSpots);
		return newParkingSpots;
	}

	public int numberOfParkingSpots() {
		int number = parkingSpots.size();
		return number;
	}

	public boolean hasParkingSpots() {
		boolean has = parkingSpots.size() > 0;
		return has;
	}

	public int indexOfParkingSpot(ParkingSpot aParkingSpot) {
		int index = parkingSpots.indexOf(aParkingSpot);
		return index;
	}

	/* Code from template association_GetMany */
	public Reservation getReservation(int index) {
		Reservation aReservation = reservations.get(index);
		return aReservation;
	}

	public List<Reservation> getReservations() {
		List<Reservation> newReservations = Collections.unmodifiableList(reservations);
		return newReservations;
	}

	public int numberOfReservations() {
		int number = reservations.size();
		return number;
	}

	public boolean hasReservations() {
		boolean has = reservations.size() > 0;
		return has;
	}

	public int indexOfReservation(Reservation aReservation) {
		int index = reservations.indexOf(aReservation);
		return index;
	}

	/* Code from template association_GetMany */
	public Review getReview(int index) {
		Review aReview = reviews.get(index);
		return aReview;
	}

	public List<Review> getReviews() {
		List<Review> newReviews = Collections.unmodifiableList(reviews);
		return newReviews;
	}

	public int numberOfReviews() {
		int number = reviews.size();
		return number;
	}

	public boolean hasReviews() {
		boolean has = reviews.size() > 0;
		return has;
	}

	public int indexOfReview(Review aReview) {
		int index = reviews.indexOf(aReview);
		return index;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfPersons() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Person addPerson(String aFist_Name, String aLast_Name, String aUserID, String aPassword, String aEmail) {
		return new Person(aFist_Name, aLast_Name, aUserID, aPassword, aEmail, this);
	}

	public boolean addPerson(Person aPerson) {
		boolean wasAdded = false;
		if (persons.contains(aPerson)) {
			return false;
		}
		ParkingManager existingParkingManager = aPerson.getParkingManager();
		boolean isNewParkingManager = existingParkingManager != null && !this.equals(existingParkingManager);
		if (isNewParkingManager) {
			aPerson.setParkingManager(this);
		} else {
			persons.add(aPerson);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removePerson(Person aPerson) {
		boolean wasRemoved = false;
		// Unable to remove aPerson, as it must always have a parkingManager
		if (!this.equals(aPerson.getParkingManager())) {
			persons.remove(aPerson);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addPersonAt(Person aPerson, int index) {
		boolean wasAdded = false;
		if (addPerson(aPerson)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfPersons()) {
				index = numberOfPersons() - 1;
			}
			persons.remove(aPerson);
			persons.add(index, aPerson);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMovePersonAt(Person aPerson, int index) {
		boolean wasAdded = false;
		if (persons.contains(aPerson)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfPersons()) {
				index = numberOfPersons() - 1;
			}
			persons.remove(aPerson);
			persons.add(index, aPerson);
			wasAdded = true;
		} else {
			wasAdded = addPersonAt(aPerson, index);
		}
		return wasAdded;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfParkingSpots() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public ParkingSpot addParkingSpot(String aPKey, int aStreet_Number, String aSteet_Name, String aPostal_Code,
			float aAvg_Rating, float aCurrent_Price, User aUser) {
		return new ParkingSpot(aPKey, aStreet_Number, aSteet_Name, aPostal_Code, aAvg_Rating, aCurrent_Price, aUser,
				this);
	}

	public boolean addParkingSpot(ParkingSpot aParkingSpot) {
		boolean wasAdded = false;
		if (parkingSpots.contains(aParkingSpot)) {
			return false;
		}
		ParkingManager existingParkingManager = aParkingSpot.getParkingManager();
		boolean isNewParkingManager = existingParkingManager != null && !this.equals(existingParkingManager);
		if (isNewParkingManager) {
			aParkingSpot.setParkingManager(this);
		} else {
			parkingSpots.add(aParkingSpot);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeParkingSpot(ParkingSpot aParkingSpot) {
		boolean wasRemoved = false;
		// Unable to remove aParkingSpot, as it must always have a parkingManager
		if (!this.equals(aParkingSpot.getParkingManager())) {
			parkingSpots.remove(aParkingSpot);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addParkingSpotAt(ParkingSpot aParkingSpot, int index) {
		boolean wasAdded = false;
		if (addParkingSpot(aParkingSpot)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfParkingSpots()) {
				index = numberOfParkingSpots() - 1;
			}
			parkingSpots.remove(aParkingSpot);
			parkingSpots.add(index, aParkingSpot);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveParkingSpotAt(ParkingSpot aParkingSpot, int index) {
		boolean wasAdded = false;
		if (parkingSpots.contains(aParkingSpot)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfParkingSpots()) {
				index = numberOfParkingSpots() - 1;
			}
			parkingSpots.remove(aParkingSpot);
			parkingSpots.add(index, aParkingSpot);
			wasAdded = true;
		} else {
			wasAdded = addParkingSpotAt(aParkingSpot, index);
		}
		return wasAdded;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfReservations() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Reservation addReservation(String aPKey, String aVehicle_Plate, Date aStart_Date, Date aEnd_Date,
			float aPrice_Paid, int aStart_Time, int aEnd_Time, User aUser, ParkingSpot aParkingSpot) {
		return new Reservation(aPKey, aVehicle_Plate, aStart_Date, aEnd_Date, aPrice_Paid, aStart_Time, aEnd_Time,
				aUser, this, aParkingSpot);
	}

	public boolean addReservation(Reservation aReservation) {
		boolean wasAdded = false;
		if (reservations.contains(aReservation)) {
			return false;
		}
		ParkingManager existingParkingManager = aReservation.getParkingManager();
		boolean isNewParkingManager = existingParkingManager != null && !this.equals(existingParkingManager);
		if (isNewParkingManager) {
			aReservation.setParkingManager(this);
		} else {
			reservations.add(aReservation);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeReservation(Reservation aReservation) {
		boolean wasRemoved = false;
		// Unable to remove aReservation, as it must always have a parkingManager
		if (!this.equals(aReservation.getParkingManager())) {
			reservations.remove(aReservation);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addReservationAt(Reservation aReservation, int index) {
		boolean wasAdded = false;
		if (addReservation(aReservation)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfReservations()) {
				index = numberOfReservations() - 1;
			}
			reservations.remove(aReservation);
			reservations.add(index, aReservation);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveReservationAt(Reservation aReservation, int index) {
		boolean wasAdded = false;
		if (reservations.contains(aReservation)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfReservations()) {
				index = numberOfReservations() - 1;
			}
			reservations.remove(aReservation);
			reservations.add(index, aReservation);
			wasAdded = true;
		} else {
			wasAdded = addReservationAt(aReservation, index);
		}
		return wasAdded;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfReviews() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Review addReview(float aRating, String aComment, ParkingSpot aParkingSpot, String id) {
		return new Review(aRating, aComment, this, aParkingSpot, id);
	}

	public boolean addReview(Review aReview) {
		boolean wasAdded = false;
		if (reviews.contains(aReview)) {
			return false;
		}
		ParkingManager existingParkingManager = aReview.getParkingManager();
		boolean isNewParkingManager = existingParkingManager != null && !this.equals(existingParkingManager);
		if (isNewParkingManager) {
			aReview.setParkingManager(this);
		} else {
			reviews.add(aReview);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeReview(Review aReview) {
		boolean wasRemoved = false;
		// Unable to remove aReview, as it must always have a parkingManager
		if (!this.equals(aReview.getParkingManager())) {
			reviews.remove(aReview);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addReviewAt(Review aReview, int index) {
		boolean wasAdded = false;
		if (addReview(aReview)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfReviews()) {
				index = numberOfReviews() - 1;
			}
			reviews.remove(aReview);
			reviews.add(index, aReview);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveReviewAt(Review aReview, int index) {
		boolean wasAdded = false;
		if (reviews.contains(aReview)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfReviews()) {
				index = numberOfReviews() - 1;
			}
			reviews.remove(aReview);
			reviews.add(index, aReview);
			wasAdded = true;
		} else {
			wasAdded = addReviewAt(aReview, index);
		}
		return wasAdded;
	}

	public void delete() {
		while (persons.size() > 0) {
			Person aPerson = persons.get(persons.size() - 1);
			aPerson.delete();
			persons.remove(aPerson);
		}

		while (parkingSpots.size() > 0) {
			ParkingSpot aParkingSpot = parkingSpots.get(parkingSpots.size() - 1);
			aParkingSpot.delete();
			parkingSpots.remove(aParkingSpot);
		}

		while (reservations.size() > 0) {
			Reservation aReservation = reservations.get(reservations.size() - 1);
			aReservation.delete();
			reservations.remove(aReservation);
		}

		while (reviews.size() > 0) {
			Review aReview = reviews.get(reviews.size() - 1);
			aReview.delete();
			reviews.remove(aReview);
		}

	}

}