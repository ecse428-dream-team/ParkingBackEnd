/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4403.5bb429550 modeling language!*/

package ca.mcgill.ecse428.parkingsystem.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

// line 35 "../../../../../../../../ump/tmp788415/model.ump"
// line 85 "../../../../../../../../ump/tmp788415/model.ump"
@Entity
@Table(name = "parkingspot")
public class ParkingSpot {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ParkingSpot Attributes
	private String pKey;
	private int street_Number;
	private String steet_Name;
	private String postal_Code;
	private float avg_Rating;
	private float current_Price;

	// ParkingSpot Associations
	@JsonProperty(access = Access.WRITE_ONLY)
	private User user;
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Reservation> reservations;
	private List<Review> reviews;
	@JsonProperty(access = Access.WRITE_ONLY)
	private ParkingManager parkingManager;

	// ------------------------
	// SETTERS
	// ------------------------
	// Necessary for hibernate. Should never be used.
	// Roger Z 01/31/2019

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	@JsonCreator
	public ParkingSpot(@JsonProperty("pkey") String aPKey, @JsonProperty("addressNumber") int aStreet_Number,
			@JsonProperty("streetName") String aSteet_Name, @JsonProperty("postalCode") String aPostal_Code,
			@JsonProperty("avgRating") float aAvg_Rating, @JsonProperty("currentPrice") float aCurrent_Price,
			@JsonProperty("user") User aUser,@JsonProperty("parkingManager") ParkingManager aParkingManager) {
		pKey = aPKey;
		street_Number = aStreet_Number;
		steet_Name = aSteet_Name;
		postal_Code = aPostal_Code;
		avg_Rating = aAvg_Rating;
		current_Price = aCurrent_Price;
		boolean didAddUser = setUser(aUser);
		if (!didAddUser) {
			throw new RuntimeException("Unable to create parkingSpot due to user");
		}
		reservations = new ArrayList<Reservation>();
		reviews = new ArrayList<Review>();
		boolean didAddParkingManager = setParkingManager(aParkingManager);
		if (!didAddParkingManager) {
			throw new RuntimeException("Unable to create parkingSpot due to parkingManager");
		}
	}

	public ParkingSpot() {

    }

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setPKey(String aPKey) {
		boolean wasSet = false;
		pKey = aPKey;
		wasSet = true;
		return wasSet;
	}

	public boolean setStreet_Number(int aStreet_Number) {
		boolean wasSet = false;
		street_Number = aStreet_Number;
		wasSet = true;
		return wasSet;
	}

	public boolean setSteet_Name(String aSteet_Name) {
		boolean wasSet = false;
		steet_Name = aSteet_Name;
		wasSet = true;
		return wasSet;
	}

	public boolean setPostal_Code(String aPostal_Code) {
		boolean wasSet = false;
		postal_Code = aPostal_Code;
		wasSet = true;
		return wasSet;
	}

	public boolean setAvg_Rating(float aAvg_Rating) {
		boolean wasSet = false;
		avg_Rating = aAvg_Rating;
		wasSet = true;
		return wasSet;
	}

	public boolean setCurrent_Price(float aCurrent_Price) {
		boolean wasSet = false;
		current_Price = aCurrent_Price;
		wasSet = true;
		return wasSet;
	}

	@Id
	public String getPKey() {
		return pKey;
	}

	public int getStreet_Number() {
		return street_Number;
	}

	public String getSteet_Name() {
		return steet_Name;
	}

	public String getPostal_Code() {
		return postal_Code;
	}

	public float getAvg_Rating() {
		return avg_Rating;
	}

	public float getCurrent_Price() {
		return current_Price;
	}

	/* Code from template association_GetOne */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "parkingSpot_user")
	public User getUser() {
		return user;
	}

	/* Code from template association_GetMany */
	public Reservation getReservation(int index) {
		Reservation aReservation = reservations.get(index);
		return aReservation;
	}

	@OneToMany(mappedBy = "parkingSpot", cascade = { CascadeType.ALL })
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

	@OneToMany(mappedBy = "parkingSpot", cascade = { CascadeType.ALL })
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

	/* Code from template association_GetOne */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "parkingSpot_parkingManager")
	public ParkingManager getParkingManager() {
		return parkingManager;
	}

	/* Code from template association_SetOneToMany */
	public boolean setUser(User aUser) {
		boolean wasSet = false;
		if (aUser == null) {
			return wasSet;
		}

		User existingUser = user;
		user = aUser;
		if (existingUser != null && !existingUser.equals(aUser)) {
			existingUser.removeParkingSpot(this);
		}
		user.addParkingSpot(this);
		wasSet = true;
		return wasSet;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfReservations() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Reservation addReservation(String aPKey, String aVehicle_Plate, Date aStart_Date, Date aEnd_Date,
			float aPrice_Paid, int aStart_Time, int aEnd_Time, User aUser, ParkingManager aParkingManager) {
		return new Reservation(aPKey, aVehicle_Plate, aStart_Date, aEnd_Date, aPrice_Paid, aStart_Time, aEnd_Time,
				aUser, aParkingManager, this);
	}

	public boolean addReservation(Reservation aReservation) {
		boolean wasAdded = false;
		if (reservations.contains(aReservation)) {
			return false;
		}
		ParkingSpot existingParkingSpot = aReservation.getParkingSpot();
		boolean isNewParkingSpot = existingParkingSpot != null && !this.equals(existingParkingSpot);
		if (isNewParkingSpot) {
			aReservation.setParkingSpot(this);
		} else {
			reservations.add(aReservation);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeReservation(Reservation aReservation) {
		boolean wasRemoved = false;
		// Unable to remove aReservation, as it must always have a parkingSpot
		if (!this.equals(aReservation.getParkingSpot())) {
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
	public Review addReview(String aPKey, float aRating, String aComment, ParkingManager aParkingManager) {
		return new Review(aPKey, aRating, aComment, aParkingManager, this);
	}

	public boolean addReview(Review aReview) {
		boolean wasAdded = false;
		if (reviews.contains(aReview)) {
			return false;
		}
		ParkingSpot existingParkingSpot = aReview.getParkingSpot();
		boolean isNewParkingSpot = existingParkingSpot != null && !this.equals(existingParkingSpot);
		if (isNewParkingSpot) {
			aReview.setParkingSpot(this);
		} else {
			reviews.add(aReview);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeReview(Review aReview) {
		boolean wasRemoved = false;
		// Unable to remove aReview, as it must always have a parkingSpot
		if (!this.equals(aReview.getParkingSpot())) {
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

	/* Code from template association_SetOneToMany */
	public boolean setParkingManager(ParkingManager aParkingManager) {
		boolean wasSet = false;
		if (aParkingManager == null) {
			return wasSet;
		}

		ParkingManager existingParkingManager = parkingManager;
		parkingManager = aParkingManager;
		if (existingParkingManager != null && !existingParkingManager.equals(aParkingManager)) {
			existingParkingManager.removeParkingSpot(this);
		}
		parkingManager.addParkingSpot(this);
		wasSet = true;
		return wasSet;
	}

	public void delete() {
		User placeholderUser = user;
		this.user = null;
		if (placeholderUser != null) {
			placeholderUser.removeParkingSpot(this);
		}
		for (int i = reservations.size(); i > 0; i--) {
			Reservation aReservation = reservations.get(i - 1);
			aReservation.delete();
		}
		while (reviews.size() > 0) {
			Review aReview = reviews.get(reviews.size() - 1);
			aReview.delete();
			reviews.remove(aReview);
		}

		ParkingManager placeholderParkingManager = parkingManager;
		this.parkingManager = null;
		if (placeholderParkingManager != null) {
			placeholderParkingManager.removeParkingSpot(this);
		}
	}

	public String toString() {
		return super.toString() + "[" + "pKey" + ":" + getPKey() + "," + "street_Number" + ":" + getStreet_Number()
				+ "," + "steet_Name" + ":" + getSteet_Name() + "," + "postal_Code" + ":" + getPostal_Code() + ","
				+ "avg_Rating" + ":" + getAvg_Rating() + "," + "current_Price" + ":" + getCurrent_Price() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "user = "
				+ (getUser() != null ? Integer.toHexString(System.identityHashCode(getUser())) : "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "parkingManager = "
				+ (getParkingManager() != null ? Integer.toHexString(System.identityHashCode(getParkingManager()))
						: "null");
	}
}