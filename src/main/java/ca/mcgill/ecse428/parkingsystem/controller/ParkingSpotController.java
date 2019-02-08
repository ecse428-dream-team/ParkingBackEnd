package ca.mcgill.ecse428.parkingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.parkingsystem.model.ParkingSpot;
import ca.mcgill.ecse428.parkingsystem.model.User;
import ca.mcgill.ecse428.parkingsystem.repository.ParkingSpotRepository;

@RestController
@RequestMapping("/spot")
public class ParkingSpotController {

	@Autowired
	ParkingSpotRepository repository;

    @GetMapping
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello", null, HttpStatus.OK);
    }
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ParkingSpot addParkingSpot(@RequestBody ParkingSpot ps) {
	    return repository.addParkingSpot(ps);
	}
	
//Make searches to create lists of parking spots with the same: Street name, street number, postal code,

	@RequestMapping(value = "/streetnumber/{streetNum}", method = RequestMethod.GET)
    public List<ParkingSpot> getSpotByStreetNumber(
            @PathVariable("streetNum") int StreetNum) {
        return repository.getSpotByStreetNumber(StreetNum);
    }
	
	
}
