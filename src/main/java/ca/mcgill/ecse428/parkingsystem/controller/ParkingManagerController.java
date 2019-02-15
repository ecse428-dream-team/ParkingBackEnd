package ca.mcgill.ecse428.parkingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.parkingsystem.model.ParkingManager;
import ca.mcgill.ecse428.parkingsystem.repository.ParkingManagerRepository;

@RestController
@RequestMapping("/manager")
public class ParkingManagerController {

	@Autowired
	ParkingManagerRepository repository;

//	AnKhang problem getting manager
//	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<ParkingManager> getParkingManager(@PathVariable String id) {
//
//		ParkingManager ParkingManagerFound = repository.getParkingManager(id);
//		return new ResponseEntity<ParkingManager>(ParkingManagerFound, null, HttpStatus.OK);
//	}
//
//	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<List<ParkingManager>> getParkingManagers() {
//		List<ParkingManager> ParkingManagers = repository.getParkingManagers();
//		return new ResponseEntity<List<ParkingManager>>(ParkingManagers, null, HttpStatus.OK);
//	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ParkingManager createUser(@RequestBody ParkingManager manager) {
		return repository.addManager(manager);
	}

}
