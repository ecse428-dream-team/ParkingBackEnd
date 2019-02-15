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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.parkingsystem.model.User;
import ca.mcgill.ecse428.parkingsystem.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository repository;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public User addUser(@RequestBody User user) {
		return repository.addUser(user);

	}

	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = repository.getAllUsers();
		return new ResponseEntity<List<User>>(users, null, HttpStatus.OK);
	}

	@RequestMapping(value = "/firstname/{firstname}", method = RequestMethod.GET)
    public List<User> getUserByFirstName(
            @PathVariable("firstname") String firstName) {
        return repository.getUserByFirstName(firstName);
    }

    @RequestMapping(value = "/lastname/{lastname}", method = RequestMethod.GET)
    public List<User> getUserByLastName(
            @PathVariable("lastname") String lastName) {
        return repository.getUserByLastName(lastName);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public List<User> getUserById(
            @PathVariable("id") String id) {
        return repository.getUserById(id);
    }

}
