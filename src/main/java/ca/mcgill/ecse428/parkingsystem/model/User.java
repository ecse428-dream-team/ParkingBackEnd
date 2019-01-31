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

// line 14 "../../../../../../../../ump/tmp864058/model.ump"
// line 69 "../../../../../../../../ump/tmp864058/model.ump"
@Entity
@Table(name="user")
public class User extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private boolean isRenter;
  private boolean isSeller;

  //User Associations
  private List<ParkingSpot> parkingSpots;
  private List<Reservation> reservations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aFist_Name, String aLast_Name, String aUserID, String aPassword, String aEmail, ParkingManager aParkingManager, boolean aIsRenter, boolean aIsSeller)
  {
    super(aFist_Name, aLast_Name, aUserID, aPassword, aEmail, aParkingManager);
    isRenter = aIsRenter;
    isSeller = aIsSeller;
    parkingSpots = new ArrayList<ParkingSpot>();
    reservations = new ArrayList<Reservation>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  
  @Id
  public String getId() {
	  return getUserID();
  }

  public boolean setIsRenter(boolean aIsRenter)
  {
    boolean wasSet = false;
    isRenter = aIsRenter;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsSeller(boolean aIsSeller)
  {
    boolean wasSet = false;
    isSeller = aIsSeller;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsRenter()
  {
    return isRenter;
  }

  public boolean getIsSeller()
  {
    return isSeller;
  }
  /* Code from template association_GetMany */
  public ParkingSpot getParkingSpot(int index)
  {
    ParkingSpot aParkingSpot = parkingSpots.get(index);
    return aParkingSpot;
  }

  public List<ParkingSpot> getParkingSpots()
  {
    List<ParkingSpot> newParkingSpots = Collections.unmodifiableList(parkingSpots);
    return newParkingSpots;
  }

  public int numberOfParkingSpots()
  {
    int number = parkingSpots.size();
    return number;
  }

  public boolean hasParkingSpots()
  {
    boolean has = parkingSpots.size() > 0;
    return has;
  }

  public int indexOfParkingSpot(ParkingSpot aParkingSpot)
  {
    int index = parkingSpots.indexOf(aParkingSpot);
    return index;
  }
  /* Code from template association_GetMany */
  public Reservation getReservation(int index)
  {
    Reservation aReservation = reservations.get(index);
    return aReservation;
  }

  public List<Reservation> getReservations()
  {
    List<Reservation> newReservations = Collections.unmodifiableList(reservations);
    return newReservations;
  }

  public int numberOfReservations()
  {
    int number = reservations.size();
    return number;
  }

  public boolean hasReservations()
  {
    boolean has = reservations.size() > 0;
    return has;
  }

  public int indexOfReservation(Reservation aReservation)
  {
    int index = reservations.indexOf(aReservation);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfParkingSpots()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ParkingSpot addParkingSpot(String aPKey, int aStreet_Number, String aSteet_Name, String aPostal_Code, float aAvg_Rating, float aCurrent_Price, ParkingManager aParkingManager)
  {
    return new ParkingSpot(aPKey, aStreet_Number, aSteet_Name, aPostal_Code, aAvg_Rating, aCurrent_Price, this, aParkingManager);
  }

  public boolean addParkingSpot(ParkingSpot aParkingSpot)
  {
    boolean wasAdded = false;
    if (parkingSpots.contains(aParkingSpot)) { return false; }
    User existingUser = aParkingSpot.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aParkingSpot.setUser(this);
    }
    else
    {
      parkingSpots.add(aParkingSpot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeParkingSpot(ParkingSpot aParkingSpot)
  {
    boolean wasRemoved = false;
    //Unable to remove aParkingSpot, as it must always have a user
    if (!this.equals(aParkingSpot.getUser()))
    {
      parkingSpots.remove(aParkingSpot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addParkingSpotAt(ParkingSpot aParkingSpot, int index)
  {  
    boolean wasAdded = false;
    if(addParkingSpot(aParkingSpot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfParkingSpots()) { index = numberOfParkingSpots() - 1; }
      parkingSpots.remove(aParkingSpot);
      parkingSpots.add(index, aParkingSpot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveParkingSpotAt(ParkingSpot aParkingSpot, int index)
  {
    boolean wasAdded = false;
    if(parkingSpots.contains(aParkingSpot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfParkingSpots()) { index = numberOfParkingSpots() - 1; }
      parkingSpots.remove(aParkingSpot);
      parkingSpots.add(index, aParkingSpot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addParkingSpotAt(aParkingSpot, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReservations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reservation addReservation(String aPKey, String aVehicle_Plate, Date aStart_Date, Date aEnd_Date, float aPrice_Paid, int aStart_Time, int aEnd_Time, ParkingManager aParkingManager, ParkingSpot aParkingSpot)
  {
    return new Reservation(aPKey, aVehicle_Plate, aStart_Date, aEnd_Date, aPrice_Paid, aStart_Time, aEnd_Time, this, aParkingManager, aParkingSpot);
  }

  public boolean addReservation(Reservation aReservation)
  {
    boolean wasAdded = false;
    if (reservations.contains(aReservation)) { return false; }
    User existingUser = aReservation.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aReservation.setUser(this);
    }
    else
    {
      reservations.add(aReservation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReservation(Reservation aReservation)
  {
    boolean wasRemoved = false;
    //Unable to remove aReservation, as it must always have a user
    if (!this.equals(aReservation.getUser()))
    {
      reservations.remove(aReservation);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReservationAt(Reservation aReservation, int index)
  {  
    boolean wasAdded = false;
    if(addReservation(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReservationAt(Reservation aReservation, int index)
  {
    boolean wasAdded = false;
    if(reservations.contains(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReservationAt(aReservation, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=parkingSpots.size(); i > 0; i--)
    {
      ParkingSpot aParkingSpot = parkingSpots.get(i - 1);
      aParkingSpot.delete();
    }
    for(int i=reservations.size(); i > 0; i--)
    {
      Reservation aReservation = reservations.get(i - 1);
      aReservation.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "isRenter" + ":" + getIsRenter()+ "," +
            "isSeller" + ":" + getIsSeller()+ "]";
  }
}