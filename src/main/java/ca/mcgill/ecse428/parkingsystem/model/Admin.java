/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4403.5bb429550 modeling language!*/

package ca.mcgill.ecse428.parkingsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// line 21 "../../../../../../../../ump/tmp864058/model.ump"
// line 74 "../../../../../../../../ump/tmp864058/model.ump"
@Entity
@Table(name="admin")
public class Admin extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Admin(String aFist_Name, String aLast_Name, String aUserID, String aPassword, String aEmail, ParkingManager aParkingManager)
  {
    super(aFist_Name, aLast_Name, aUserID, aPassword, aEmail, aParkingManager);
  }

  //------------------------
  // INTERFACE
  //------------------------
  
  @Id
  public String getId() {
	  return getUserID();
  }

  public void delete()
  {
    super.delete();
  }

}