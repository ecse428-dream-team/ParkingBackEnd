package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains repository methods for the Admin class
 * 
 */

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.Admin;

@Repository
public class AdminRepository {

	@Autowired
	EntityManager entityManager;
	
	/**
	 * Create and add an Admin to the database.
	 * 
	 * @param adm Admin to be added.
	 * @return Returns the Admin that was added. 
	 */
	@Transactional
	public Admin addAdmin(Admin adm) {
		entityManager.persist(adm);
		return adm;
	}
	
	/**
	 * Get an Admin given his id.
	 * 
	 * @param id Id of the desired Admin. 
	 * @return Returns the desired Admin
	 */
	@Transactional
	public Admin getAdmin(String id)
	{
		Admin foundAdmin = entityManager.find(Admin.class, id);
		return foundAdmin;
	}

	/**
	 * Get all Admins that exist in the database
	 * 
	 * @return Returns a list of all existing Admins in the database. 
	 */
	@Transactional
	public List<Admin> getAdmins(){
		List<Admin> admins = new ArrayList<Admin>(); 
		admins = entityManager.createQuery("SELECT a FROM Admin a").getResultList();
		return admins;
	}
}
