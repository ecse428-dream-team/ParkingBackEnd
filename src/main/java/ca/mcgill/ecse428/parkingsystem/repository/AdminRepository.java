package ca.mcgill.ecse428.parkingsystem.repository;

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
	
}
