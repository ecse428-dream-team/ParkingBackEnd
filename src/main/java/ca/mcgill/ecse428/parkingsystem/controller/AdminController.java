package ca.mcgill.ecse428.parkingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.parkingsystem.model.Admin;
import ca.mcgill.ecse428.parkingsystem.repository.AdminRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminRepository repository;
	
    @GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Admin> getAdmin(@PathVariable String id) {
    	
    	Admin adminFound = repository.getAdmin(id);
        return new ResponseEntity<Admin>(adminFound, null, HttpStatus.OK);

    }
	
    @GetMapping(path="/all", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Admin>>getAdmins()
	{
		List<Admin> admins = repository.getAdmins();
		return new ResponseEntity<List<Admin>>(admins, null, HttpStatus.OK);
	}
    
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Admin addAdmin(@RequestBody Admin adm) {
	    return repository.addAdmin(adm);
	}

}
