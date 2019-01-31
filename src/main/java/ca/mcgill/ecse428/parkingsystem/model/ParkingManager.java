/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4403.5bb429550 modeling language!*/

package ca.mcgill.ecse428.parkingsystem.model;
import java.util.*;
import java.sql.Date;

// line 4 "../../../../../../../../ump/tmp788415/model.ump"
// line 104 "../../../../../../../../ump/tmp788415/model.ump"
// line 110 "../../../../../../../../ump/tmp788415/model.ump"
public class ParkingManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ParkingManager Attributes
  private String pKey;

  //ParkingManager Associations
  private List<User> users;
  private List<Admin> admins;
  private List<ParkingSpot> parkingSpots;
  private List<Reservation> reservations;
  private List<Review> reviews;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ParkingManager(String aPKey)
  {
    pKey = aPKey;
    users = new ArrayList<User>();
    admins = new ArrayList<Admin>();
    parkingSpots = new ArrayList<ParkingSpot>();
    reservations = new ArrayList<Reservation>();
    reviews = new ArrayList<Review>();
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

  public String getPKey()
  {
    return pKey;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public Admin getAdmin(int index)
  {
    Admin aAdmin = admins.get(index);
    return aAdmin;
  }

  public List<Admin> getAdmins()
  {
    List<Admin> newAdmins = Collections.unmodifiableList(admins);
    return newAdmins;
  }

  public int numberOfAdmins()
  {
    int number = admins.size();
    return number;
  }

  public boolean hasAdmins()
  {
    boolean has = admins.size() > 0;
    return has;
  }

  public int indexOfAdmin(Admin aAdmin)
  {
    int index = admins.indexOf(aAdmin);
    return index;
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
  /* Code from template association_GetMany */
  public Review getReview(int index)
  {
    Review aReview = reviews.get(index);
    return aReview;
  }

  public List<Review> getReviews()
  {
    List<Review> newReviews = Collections.unmodifiableList(reviews);
    return newReviews;
  }

  public int numberOfReviews()
  {
    int number = reviews.size();
    return number;
  }

  public boolean hasReviews()
  {
    boolean has = reviews.size() > 0;
    return has;
  }

  public int indexOfReview(Review aReview)
  {
    int index = reviews.indexOf(aReview);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(String aFist_Name, String aLast_Name, String aUserID, String aPassword, String aEmail, boolean aIsRenter, boolean aIsSeller)
  {
    return new User(aFist_Name, aLast_Name, aUserID, aPassword, aEmail, aIsRenter, aIsSeller, this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    ParkingManager existingParkingManager = aUser.getParkingManager();
    boolean isNewParkingManager = existingParkingManager != null && !this.equals(existingParkingManager);
    if (isNewParkingManager)
    {
      aUser.setParkingManager(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a parkingManager
    if (!this.equals(aUser.getParkingManager()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAdmins()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Admin addAdmin(String aFist_Name, String aLast_Name, String aUserID, String aPassword, String aEmail)
  {
    return new Admin(aFist_Name, aLast_Name, aUserID, aPassword, aEmail, this);
  }

  public boolean addAdmin(Admin aAdmin)
  {
    boolean wasAdded = false;
    if (admins.contains(aAdmin)) { return false; }
    ParkingManager existingParkingManager = aAdmin.getParkingManager();
    boolean isNewParkingManager = existingParkingManager != null && !this.equals(existingParkingManager);
    if (isNewParkingManager)
    {
      aAdmin.setParkingManager(this);
    }
    else
    {
      admins.add(aAdmin);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAdmin(Admin aAdmin)
  {
    boolean wasRemoved = false;
    //Unable to remove aAdmin, as it must always have a parkingManager
    if (!this.equals(aAdmin.getParkingManager()))
    {
      admins.remove(aAdmin);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAdminAt(Admin aAdmin, int index)
  {  
    boolean wasAdded = false;
    if(addAdmin(aAdmin))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAdmins()) { index = numberOfAdmins() - 1; }
      admins.remove(aAdmin);
      admins.add(index, aAdmin);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAdminAt(Admin aAdmin, int index)
  {
    boolean wasAdded = false;
    if(admins.contains(aAdmin))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAdmins()) { index = numberOfAdmins() - 1; }
      admins.remove(aAdmin);
      admins.add(index, aAdmin);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAdminAt(aAdmin, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfParkingSpots()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ParkingSpot addParkingSpot(String aPKey, int aStreet_Number, String aSteet_Name, String aPostal_Code, float aAvg_Rating, float aCurrent_Price, User aUser)
  {
    return new ParkingSpot(aPKey, aStreet_Number, aSteet_Name, aPostal_Code, aAvg_Rating, aCurrent_Price, aUser, this);
  }

  public boolean addParkingSpot(ParkingSpot aParkingSpot)
  {
    boolean wasAdded = false;
    if (parkingSpots.contains(aParkingSpot)) { return false; }
    ParkingManager existingParkingManager = aParkingSpot.getParkingManager();
    boolean isNewParkingManager = existingParkingManager != null && !this.equals(existingParkingManager);
    if (isNewParkingManager)
    {
      aParkingSpot.setParkingManager(this);
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
    //Unable to remove aParkingSpot, as it must always have a parkingManager
    if (!this.equals(aParkingSpot.getParkingManager()))
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
  public Reservation addReservation(String aPKey, String aVehicle_Plate, Date aStart_Date, Date aEnd_Date, float aPrice_Paid, int aStart_Time, int aEnd_Time, User aUser, ParkingSpot aParkingSpot)
  {
    return new Reservation(aPKey, aVehicle_Plate, aStart_Date, aEnd_Date, aPrice_Paid, aStart_Time, aEnd_Time, aUser, this, aParkingSpot);
  }

  public boolean addReservation(Reservation aReservation)
  {
    boolean wasAdded = false;
    if (reservations.contains(aReservation)) { return false; }
    ParkingManager existingParkingManager = aReservation.getParkingManager();
    boolean isNewParkingManager = existingParkingManager != null && !this.equals(existingParkingManager);
    if (isNewParkingManager)
    {
      aReservation.setParkingManager(this);
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
    //Unable to remove aReservation, as it must always have a parkingManager
    if (!this.equals(aReservation.getParkingManager()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReviews()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Review addReview(String aPKey, float aRating, String aComment, ParkingSpot aParkingSpot)
  {
    return new Review(aPKey, aRating, aComment, this, aParkingSpot);
  }

  public boolean addReview(Review aReview)
  {
    boolean wasAdded = false;
    if (reviews.contains(aReview)) { return false; }
    ParkingManager existingParkingManager = aReview.getParkingManager();
    boolean isNewParkingManager = existingParkingManager != null && !this.equals(existingParkingManager);
    if (isNewParkingManager)
    {
      aReview.setParkingManager(this);
    }
    else
    {
      reviews.add(aReview);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReview(Review aReview)
  {
    boolean wasRemoved = false;
    //Unable to remove aReview, as it must always have a parkingManager
    if (!this.equals(aReview.getParkingManager()))
    {
      reviews.remove(aReview);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReviewAt(Review aReview, int index)
  {  
    boolean wasAdded = false;
    if(addReview(aReview))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReviews()) { index = numberOfReviews() - 1; }
      reviews.remove(aReview);
      reviews.add(index, aReview);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReviewAt(Review aReview, int index)
  {
    boolean wasAdded = false;
    if(reviews.contains(aReview))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReviews()) { index = numberOfReviews() - 1; }
      reviews.remove(aReview);
      reviews.add(index, aReview);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReviewAt(aReview, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (admins.size() > 0)
    {
      Admin aAdmin = admins.get(admins.size() - 1);
      aAdmin.delete();
      admins.remove(aAdmin);
    }
    
    while (parkingSpots.size() > 0)
    {
      ParkingSpot aParkingSpot = parkingSpots.get(parkingSpots.size() - 1);
      aParkingSpot.delete();
      parkingSpots.remove(aParkingSpot);
    }
    
    while (reservations.size() > 0)
    {
      Reservation aReservation = reservations.get(reservations.size() - 1);
      aReservation.delete();
      reservations.remove(aReservation);
    }
    
    while (reviews.size() > 0)
    {
      Review aReview = reviews.get(reviews.size() - 1);
      aReview.delete();
      reviews.remove(aReview);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "pKey" + ":" + getPKey()+ "]";
  }
}