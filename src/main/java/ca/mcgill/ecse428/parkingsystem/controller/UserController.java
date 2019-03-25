package ca.mcgill.ecse428.parkingsystem.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse428.parkingsystem.model.User;
import ca.mcgill.ecse428.parkingsystem.repository.UserRepository;

/**
 * Contains endpoints for the User class.
 *
 */

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository repository;

	/**
	 * Calls method to get create and add a user to the database.
	 * 
	 * @param user
	 *            User to be created and added to the database
	 * @return ResponseEntity<String> Confirmation of the creation of the User.
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> addUser(@RequestBody User user) {

		List<User> users = repository.getAllUsers();

		for (User currUser : users) {
			if (currUser.getUserID().equals(user.getUserID())) {
				return new ResponseEntity<String>("User with that id already exists", HttpStatus.BAD_REQUEST);
			} else if (currUser.getEmail().equals(user.getEmail())) {
				return new ResponseEntity<String>("User with that email already exists", HttpStatus.BAD_REQUEST);
			}
		}

		repository.addUser(user);

		return new ResponseEntity<String>("User Created", HttpStatus.OK);

	}

	/**
	 * Calls a method that Checks if the credentials of the user correspond to its credentials in the
	 * database. Checks the authenticity of the username and password.
	 * 
	 * @param authorization Base 64 encoded credentials used to authenticate the user.
	 * @param method The method of authentication. Either email or username
	 * @return ResponseEntity<> Returns a response saying if the user is authorized or not 
	 */
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

			if (method.toLowerCase().equals("username")) {
				User user = authenticateWithUesrname(values[0], values[1]);
				if (user != null) {
					return new ResponseEntity<>(user, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(user, HttpStatus.UNAUTHORIZED);
				}
			} else if (method.toLowerCase().equals("email")) {
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

	/**
	 * Calls a method that gets a user by his first name
	 * 
	 * @param firstName First Name used to look up the user. 
	 * @return User Returns the user with the corresponding First Name
	 */
	@RequestMapping(value = "/firstname/{firstname}", method = RequestMethod.GET)
	public List<User> getUserByFirstName(@PathVariable("firstname") String firstName) {
		return repository.getUserByFirstName(firstName);
	}

	/**
	 * Calls a method that gets a user by his last name
	 * 
	 * @param lastName Last Name used to look up the user
	 * @return User Returns the user with the corresponding Last Name
	 */
	@RequestMapping(value = "/lastname/{lastname}", method = RequestMethod.GET)
	public List<User> getUserByLastName(@PathVariable("lastname") String lastName) {
		return repository.getUserByLastName(lastName);
	}

	/**
	 * Calls a method that gets a user by his id
	 * 
	 * @param id Id used to look up the user
	 * @return ResponseEntity<> Returns the user if he is found. Returns an error message otherwise.
	 */
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public ResponseEntity getUserById(@PathVariable("id") String id) {
		User user = repository.getUserById(id);
		if (user == null) {
			return new ResponseEntity<>("No user with that id found", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}

	/**
	 * Calls a method to get all user in the database
	 * 
	 * @return ResponseEntity<List<User>> Returns a list of all users existing in the database. 
	 */
	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = repository.getAllUsers();
		return new ResponseEntity<List<User>>(users, null, HttpStatus.OK);
	}

	/**
	 * Delete a user by its id.
	 * 
	 * @param username Id of the user to be deleted. 
	 * @return ResponseEntity<String> Returns a confirmation of the deletion of the user. 
	 */
	@DeleteMapping(value = "/id/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") String username) {
		boolean isDeleted = repository.deleteUser(username);
		if (isDeleted)
			return new ResponseEntity<String>("User succesfully deleted", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("User could not be found", HttpStatus.BAD_REQUEST);
	}

}
