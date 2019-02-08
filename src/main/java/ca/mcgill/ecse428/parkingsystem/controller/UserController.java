package ca.mcgill.ecse428.parkingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse428.parkingsystem.model.User;
import ca.mcgill.ecse428.parkingsystem.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository repository;
	
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello", null, HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public User addUser(@RequestBody User user) {
        return repository.addUser(user);
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
