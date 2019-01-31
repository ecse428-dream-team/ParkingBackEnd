/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4403.5bb429550 modeling language!*/

package ca.mcgill.ecse428.parkingsystem.model;
import java.sql.Date;

// line 49 "../../../../../../../../ump/tmp788415/model.ump"
// line 93 "../../../../../../../../ump/tmp788415/model.ump"
public class Reservation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private String pKey;
  private String vehicle_Plate;
  private Date start_Date;
  private Date end_Date;
  private float price_Paid;
  private int start_Time;
  private int end_Time;

  //Reservation Associations
  private User user;
  private ParkingManager parkingManager;
  private ParkingSpot parkingSpot;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(String aPKey, String aVehicle_Plate, Date aStart_Date, Date aEnd_Date, float aPrice_Paid, int aStart_Time, int aEnd_Time, User aUser, ParkingManager aParkingManager, ParkingSpot aParkingSpot)
  {
    pKey = aPKey;
    vehicle_Plate = aVehicle_Plate;
    start_Date = aStart_Date;
    end_Date = aEnd_Date;
    price_Paid = aPrice_Paid;
    start_Time = aStart_Time;
    end_Time = aEnd_Time;
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create reservation due to user");
    }
    boolean didAddParkingManager = setParkingManager(aParkingManager);
    if (!didAddParkingManager)
    {
      throw new RuntimeException("Unable to create reservation due to parkingManager");
    }
    boolean didAddParkingSpot = setParkingSpot(aParkingSpot);
    if (!didAddParkingSpot)
    {
      throw new RuntimeException("Unable to create reservation due to parkingSpot");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPKey(String aPKey)
  {
    boolean wasSet = false;
    pKey = aPKey;
    wasSet = true;
    return wasSet;
  }

  public boolean setVehicle_Plate(String aVehicle_Plate)
  {
    boolean wasSet = false;
    vehicle_Plate = aVehicle_Plate;
    wasSet = true;
    return wasSet;
  }

  public boolean setStart_Date(Date aStart_Date)
  {
    boolean wasSet = false;
    start_Date = aStart_Date;
    wasSet = true;
    return wasSet;
  }

  public boolean setEnd_Date(Date aEnd_Date)
  {
    boolean wasSet = false;
    end_Date = aEnd_Date;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice_Paid(float aPrice_Paid)
  {
    boolean wasSet = false;
    price_Paid = aPrice_Paid;
    wasSet = true;
    return wasSet;
  }

  public boolean setStart_Time(int aStart_Time)
  {
    boolean wasSet = false;
    start_Time = aStart_Time;
    wasSet = true;
    return wasSet;
  }

  public boolean setEnd_Time(int aEnd_Time)
  {
    boolean wasSet = false;
    end_Time = aEnd_Time;
    wasSet = true;
    return wasSet;
  }

  public String getPKey()
  {
    return pKey;
  }

  public String getVehicle_Plate()
  {
    return vehicle_Plate;
  }

  public Date getStart_Date()
  {
    return start_Date;
  }

  public Date getEnd_Date()
  {
    return end_Date;
  }

  public float getPrice_Paid()
  {
    return price_Paid;
  }

  public int getStart_Time()
  {
    return start_Time;
  }

  public int getEnd_Time()
  {
    return end_Time;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_GetOne */
  public ParkingManager getParkingManager()
  {
    return parkingManager;
  }
  /* Code from template association_GetOne */
  public ParkingSpot getParkingSpot()
  {
    return parkingSpot;
  }
  /* Code from template association_SetOneToMany */
  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    if (aUser == null)
    {
      return wasSet;
    }

    User existingUser = user;
    user = aUser;
    if (existingUser != null && !existingUser.equals(aUser))
    {
      existingUser.removeReservation(this);
    }
    user.addReservation(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setParkingManager(ParkingManager aParkingManager)
  {
    boolean wasSet = false;
    if (aParkingManager == null)
    {
      return wasSet;
    }

    ParkingManager existingParkingManager = parkingManager;
    parkingManager = aParkingManager;
    if (existingParkingManager != null && !existingParkingManager.equals(aParkingManager))
    {
      existingParkingManager.removeReservation(this);
    }
    parkingManager.addReservation(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setParkingSpot(ParkingSpot aParkingSpot)
  {
    boolean wasSet = false;
    if (aParkingSpot == null)
    {
      return wasSet;
    }

    ParkingSpot existingParkingSpot = parkingSpot;
    parkingSpot = aParkingSpot;
    if (existingParkingSpot != null && !existingParkingSpot.equals(aParkingSpot))
    {
      existingParkingSpot.removeReservation(this);
    }
    parkingSpot.addReservation(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    User placeholderUser = user;
    this.user = null;
    if(placeholderUser != null)
    {
      placeholderUser.removeReservation(this);
    }
    ParkingManager placeholderParkingManager = parkingManager;
    this.parkingManager = null;
    if(placeholderParkingManager != null)
    {
      placeholderParkingManager.removeReservation(this);
    }
    ParkingSpot placeholderParkingSpot = parkingSpot;
    this.parkingSpot = null;
    if(placeholderParkingSpot != null)
    {
      placeholderParkingSpot.removeReservation(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "pKey" + ":" + getPKey()+ "," +
            "vehicle_Plate" + ":" + getVehicle_Plate()+ "," +
            "price_Paid" + ":" + getPrice_Paid()+ "," +
            "start_Time" + ":" + getStart_Time()+ "," +
            "end_Time" + ":" + getEnd_Time()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "start_Date" + "=" + (getStart_Date() != null ? !getStart_Date().equals(this)  ? getStart_Date().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "end_Date" + "=" + (getEnd_Date() != null ? !getEnd_Date().equals(this)  ? getEnd_Date().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "parkingManager = "+(getParkingManager()!=null?Integer.toHexString(System.identityHashCode(getParkingManager())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "parkingSpot = "+(getParkingSpot()!=null?Integer.toHexString(System.identityHashCode(getParkingSpot())):"null");
  }
}