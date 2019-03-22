/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4403.5bb429550 modeling language!*/

package ca.mcgill.ecse428.parkingsystem.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

// line 61 "../../../../../../../../ump/tmp788415/model.ump"
// line 99 "../../../../../../../../ump/tmp788415/model.ump"
@Entity
@Table(name = "review")
public class Review {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Review Attributes
	private int pKey;
	private float rating;
	private String comment;

	// Review Associations
	@JsonProperty(access = Access.WRITE_ONLY)
	private ParkingManager parkingManager;
	@JsonProperty(access = Access.WRITE_ONLY)
	private ParkingSpot parkingSpot;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------
	@JsonCreator
	public Review(@JsonProperty("rating") float aRating,
			@JsonProperty("comment") String aComment, @JsonProperty("parkingManager") ParkingManager aParkingManager,
			@JsonProperty("spot") ParkingSpot aParkingSpot) {
		rating = aRating;
		comment = aComment;
		boolean didAddParkingManager = setParkingManager(aParkingManager);
		if (!didAddParkingManager) {
			throw new RuntimeException("Unable to create review due to parkingManager");
		}
		boolean didAddParkingSpot = setParkingSpot(aParkingSpot);
		if (!didAddParkingSpot) {
			throw new RuntimeException("Unable to create review due to parkingSpot");
		}
	}
	
	public Review() {}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setPKey(int aPKey) {
		boolean wasSet = false;
		pKey = aPKey;
		wasSet = true;
		return wasSet;
	}

	public boolean setRating(float aRating) {
		boolean wasSet = false;
		rating = aRating;
		wasSet = true;
		return wasSet;
	}

	public boolean setComment(String aComment) {
		boolean wasSet = false;
		comment = aComment;
		wasSet = true;
		return wasSet;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int getPKey() {
		return pKey;
	}

	public float getRating() {
		return rating;
	}

	public String getComment() {
		return comment;
	}

	/* Code from template association_GetOne */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "review_parkingManager")
	public ParkingManager getParkingManager() {
		return parkingManager;
	}

	/* Code from template association_GetOne */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "review_parkingSpot")
	public ParkingSpot getParkingSpot() {
		return parkingSpot;
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
			existingParkingManager.removeReview(this);
		}
		parkingManager.addReview(this);
		wasSet = true;
		return wasSet;
	}

	/* Code from template association_SetOneToMany */
	public boolean setParkingSpot(ParkingSpot aParkingSpot) {
		boolean wasSet = false;
		if (aParkingSpot == null) {
			return wasSet;
		}

		ParkingSpot existingParkingSpot = parkingSpot;
		parkingSpot = aParkingSpot;
		if (existingParkingSpot != null && !existingParkingSpot.equals(aParkingSpot)) {
			existingParkingSpot.removeReview(this);
		}
		parkingSpot.addReview(this);
		wasSet = true;
		return wasSet;
	}

	public void delete() {
		ParkingManager placeholderParkingManager = parkingManager;
		this.parkingManager = null;
		if (placeholderParkingManager != null) {
			placeholderParkingManager.removeReview(this);
		}
		ParkingSpot placeholderParkingSpot = parkingSpot;
		this.parkingSpot = null;
		if (placeholderParkingSpot != null) {
			placeholderParkingSpot.removeReview(this);
		}
	}

	public String toString() {
		return super.toString() + "[" + "pKey" + ":" + getPKey() + "," + "rating" + ":" + getRating() + "," + "comment"
				+ ":" + getComment() + "]" + System.getProperties().getProperty("line.separator") + "  "
				+ "parkingManager = "
				+ (getParkingManager() != null ? Integer.toHexString(System.identityHashCode(getParkingManager()))
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "parkingSpot = "
				+ (getParkingSpot() != null ? Integer.toHexString(System.identityHashCode(getParkingSpot())) : "null");
	}
}