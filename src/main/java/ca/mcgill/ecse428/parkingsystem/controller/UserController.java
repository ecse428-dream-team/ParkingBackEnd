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

		for (User currUser : users) {
			if (currUser.getUserID().equals(user.getUserID())) {
				return new ResponseEntity("User with that id already exists", HttpStatus.BAD_REQUEST);
			} else if (currUser.getEmail().equals(user.getEmail())) {
				return new ResponseEntity("User with that email already exists", HttpStatus.BAD_REQUEST);
			}
		}

		repository.addUser(user);

		return new ResponseEntity("User Created", HttpStatus.OK);

	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<User> authenticateUser(@RequestHeader("Authorization") String authorization,
			@RequestHeader("Method") String method) {
		if (authorization != null && authorization.toLowerCase().startsWith("basic") && method != null) {
			// Authorization: Basic base64credentials
			String base64Credentials = authorization.substring("Basic".length()).trim();
			byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
			String credentials = new String(credDecoded, StandardCharsets.UTF_8);
			// credentials = username:password
			String[] values = credentials.split(":", 2);

			if(method.toLowerCase().equals("username")) {
				User user = authenticateWithUesrname(values[0], values[1]);
				if (user != null) {
					return new ResponseEntity<>(user, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(user, HttpStatus.UNAUTHORIZED);
				}
			} else if(method.toLowerCase().equals("email")) {
				User user = authenticateWithEmail(values[0], values[1]);
				if (user != null) {
					return new ResponseEntity<>(user, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

		} else {
			System.out.println("======================================");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	private User authenticateWithUesrname(String username, String password) {
		if (username != null && password != null) {
			try {
				User user = repository.getUserById(username);
				if (user.getPassword().equals(password)) {
					return user;
				} else {
					return null;
				}
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}
	
	private User authenticateWithEmail(String email, String password) {
		if (email != null && password != null) {
			try {
				User user = repository.getUserByEmail(email);
				if (user.getPassword().equals(password)) {
					return user;
				} else {
					return null;
				}
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/firstname/{firstname}", method = RequestMethod.GET)
	public List<User> getUserByFirstName(@PathVariable("firstname") String firstName) {
		return repository.getUserByFirstName(firstName);
	}

	@RequestMapping(value = "/lastname/{lastname}", method = RequestMethod.GET)
	public List<User> getUserByLastName(@PathVariable("lastname") String lastName) {
		return repository.getUserByLastName(lastName);
	}

	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable("id") String id) {
		return repository.getUserById(id);
	}

	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = repository.getAllUsers();
		return new ResponseEntity<List<User>>(users, null, HttpStatus.OK);
	}

	@DeleteMapping(value = "/id/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") String username) {
		boolean isDeleted = repository.deleteUser(username);
		if (isDeleted)
			return new ResponseEntity<String>("User succesfully deleted", HttpStatus.OK);
		else
			return new ResponseEntity<String>("User could not be found", HttpStatus.BAD_REQUEST);
	}

}
