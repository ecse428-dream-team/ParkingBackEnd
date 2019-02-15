package ca.mcgill.ecse428.parkingsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.parkingsystem.model.Admin;

@Repository
public class AdminRepository {

	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public Admin addAdmin(Admin adm) {
		entityManager.persist(adm);
		return adm;
	}
	
	@Transactional
	public Admin getAdmin(String id)
	{
		Admin foundAdmin = entityManager.find(Admin.class, id);
		return foundAdmin;
	}

	@Transactional
	public List<Admin> getAdmins(){
		List<Admin> admins = new ArrayList<Admin>(); 
		admins = entityManager.createQuery("SELECT a FROM Admin a").getResultList();
		return admins;
	}
}
