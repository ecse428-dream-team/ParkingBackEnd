/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4403.5bb429550 modeling language!*/

package ca.mcgill.ecse428.parkingsystem.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

// line 25 "../../../../../../../../ump/tmp788415/model.ump"
// line 80 "../../../../../../../../ump/tmp788415/model.ump"
@MappedSuperclass
public abstract class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String first_name;
  private String last_Name;
  private String userID;
  private String password;
  private String email;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String afirst_name, String aLast_Name, String aUserID, String aPassword, String aEmail)
  {
    first_name = afirst_name;
    last_Name = aLast_Name;
    userID = aUserID;
    password = aPassword;
    email = aEmail;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setfirst_name(String afirst_name)
  {
    boolean wasSet = false;
    first_name = afirst_name;
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

  public String getfirst_name()
  {
    return first_name;
  }

  public String getLast_Name()
  {
    return last_Name;
  }

  @Id
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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "first_name" + ":" + getfirst_name()+ "," +
            "last_Name" + ":" + getLast_Name()+ "," +
            "userID" + ":" + getUserID()+ "," +
            "password" + ":" + getPassword()+ "," +
            "email" + ":" + getEmail()+ "]";
  }
}