/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4403.5bb429550 modeling language!*/

package ca.mcgill.ecse428.parkingsystem.model;

// line 26 "../../../../../../../../ump/tmp864058/model.ump"
// line 79 "../../../../../../../../ump/tmp864058/model.ump"
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String fist_Name;
  private String last_Name;
  private String userID;
  private String password;
  private String email;

  //Person Associations
  private ParkingManager parkingManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aFist_Name, String aLast_Name, String aUserID, String aPassword, String aEmail, ParkingManager aParkingManager)
  {
    fist_Name = aFist_Name;
    last_Name = aLast_Name;
    userID = aUserID;
    password = aPassword;
    email = aEmail;
    boolean didAddParkingManager = setParkingManager(aParkingManager);
    if (!didAddParkingManager)
    {
      throw new RuntimeException("Unable to create person due to parkingManager");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFist_Name(String aFist_Name)
  {
    boolean wasSet = false;
    fist_Name = aFist_Name;
    wasSet = true;
    return wasSet;
  }

  public boolean setLast_Name(String aLast_Name)
  {
    boolean wasSet = false;
    last_Name = aLast_Name;
    wasSet = true;
    return wasSet;
  }

  public boolean setUserID(String aUserID)
  {
    boolean wasSet = false;
    userID = aUserID;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public String getFist_Name()
  {
    return fist_Name;
  }

  public String getLast_Name()
  {
    return last_Name;
  }

  public String getUserID()
  {
    return userID;
  }

  public String getPassword()
  {
    return password;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template association_GetOne */
  public ParkingManager getParkingManager()
  {
    return parkingManager;
  }
  /* Code from template association_GetOne_clear */
  protected void clear_parkingManager()
  {
    parkingManager = null;
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
      existingParkingManager.removePerson(this);
    }
    parkingManager.addPerson(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ParkingManager placeholderParkingManager = parkingManager;
    this.parkingManager = null;
    if(placeholderParkingManager != null)
    {
      placeholderParkingManager.removePerson(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "fist_Name" + ":" + getFist_Name()+ "," +
            "last_Name" + ":" + getLast_Name()+ "," +
            "userID" + ":" + getUserID()+ "," +
            "password" + ":" + getPassword()+ "," +
            "email" + ":" + getEmail()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "parkingManager = "+(getParkingManager()!=null?Integer.toHexString(System.identityHashCode(getParkingManager())):"null");
  }
}