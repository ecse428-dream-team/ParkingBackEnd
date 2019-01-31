/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4403.5bb429550 modeling language!*/

package ca.mcgill.ecse428.parkingsystem.model;

// line 20 "../../../../../../../../ump/tmp788415/model.ump"
// line 75 "../../../../../../../../ump/tmp788415/model.ump"
public class Admin extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Admin Associations
  private ParkingManager parkingManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Admin(String aFist_Name, String aLast_Name, String aUserID, String aPassword, String aEmail, ParkingManager aParkingManager)
  {
    super(aFist_Name, aLast_Name, aUserID, aPassword, aEmail);
    boolean didAddParkingManager = setParkingManager(aParkingManager);
    if (!didAddParkingManager)
    {
      throw new RuntimeException("Unable to create admin due to parkingManager");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public ParkingManager getParkingManager()
  {
    return parkingManager;
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
      existingParkingManager.removeAdmin(this);
    }
    parkingManager.addAdmin(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ParkingManager placeholderParkingManager = parkingManager;
    this.parkingManager = null;
    if(placeholderParkingManager != null)
    {
      placeholderParkingManager.removeAdmin(this);
    }
    super.delete();
  }

}