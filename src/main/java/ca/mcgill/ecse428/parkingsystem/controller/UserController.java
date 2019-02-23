package ca.mcgill.ecse428.parkingsystem.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse428.parkingsystem.model.Review;
import ca.mcgill.ecse428.parkingsystem.model.User;
import ca.mcgill.ecse428.parkingsystem.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository repository;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity addUser(@RequestBody User user) {

	    List<User> users = repository.getAllUsers();

	    for(User currUser: users) {
	        if(currUser.getUserID().equals(user.getUserID())) {
	            return new ResponseEntity("User with that id already exists", HttpStatus.BAD_REQUEST);
            } else if(currUser.getEmail().equals(user.getEmail())) {
                return new ResponseEntity("User with that email already exists", HttpStatus.BAD_REQUEST);
            }
        }

        repository.addUser(user);

		return new ResponseEntity("User Created", HttpStatus.OK);

	}
	
	// Put method, for editing a user. This assumes that we never change the Id
	
	@PutMapping(path = "/edit", consumes = "application/json", produces = "application/json")
	public ResponseEntity editUser(@RequestBody User user) {
		User oldUser = repository.getUserById(user.getUserID()); //contains all old info
		User newUser = user; //contains all the new info
		repository.editUser(oldUser, newUser);
		return new ResponseEntity("User information has been updated!", HttpStatus.OK);
	}
	
	// End put method

	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = repository.getAllUsers();
		return new ResponseEntity<List<User>>(users, null, HttpStatus.OK);
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<User> authenticateUser(
            @RequestHeader("Authorization") String authorization
    ) {
        if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
            // Authorization: Basic base64credentials
            String base64Credentials = authorization.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            // credentials = username:password
            String[] values = credentials.split(":", 2);

            User user = authenticate(values[0], values[1]);

            if(user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private User authenticate(String username, String password) {
	    if(username != null && password != null) {
	        try {
                User user = repository.getUserById(username);
                if(user.getPassword().equals(password)) {
                    return user;
                } else {
                    return null;
                }
            } catch(Exception e) {
	            return null;
            }
        } else {
	        return null;
        }
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
    public User getUserById(
            @PathVariable("id") String id) {
        return repository.getUserById(id);
    }
    
	// Method(s) below is for deletion of a user object from database.
	
	// this method only deletes the user
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		User delUser = repository.getUserById(id);
		repository.deleteUser(delUser);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// End delete method(s)

}
