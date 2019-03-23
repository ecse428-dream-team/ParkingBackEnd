package ca.mcgill.ecse428.parkingsystem.ControllerTests;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ca.mcgill.ecse428.parkingsystem.model.ParkingManager;
import ca.mcgill.ecse428.parkingsystem.repository.ParkingManagerRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ParkingManagerRepository pmr;
	

    String user1 = "{\"firstName\":\"John\","
    		+ "\"lastName\":\"Doe\",\"id\":\"428\","
    		+ "\"password\":\"scrum\",\"email\":\"john.doe@mail.mcgill.ca\","
    		+ "\"isRenter\":\"true\",\"isSeller\":\"false\","
    		+ "\"parkingManager\":{\"pkey\":\"1\"}}";
    
    String parkingSpot1 = "{\"pkey\":\"321\",\"addressNumber\":\"1234\","
    		+ "\"streetName\":\"Kennedy\",\"postalCode\":\"H0H 0H0\","
    		+ "\"avgRating\":\"0\",\"currentPrice\":\"20\","
    		+ "\"user\":{\"firstName\":\"John\",\"lastName\":\"Doe\","
    		+ "\"id\":\"428\",\"password\":\"scrum\","
    		+ "\"email\":\"john.doe@mail.mcgill.ca\","
    		+ "\"isRenter\":\"true\",\"isSeller\":\"false\","
    		+ "\"parkingManager\":{\"pkey\":\"1\"}},"
    		+ "\"parkingManager\":{\"pkey\":\"1\"}}";
    
    String reservation1 = "{\"pkey\":\"888\",\"plate\":\"G1G 3A7\","
    		+ "\"startDate\":\"2020-08-28 21:00:00\","
    		+ "\"endDate\":\"2020-08-29 11:00:00\","
    		+ "\"pricePaid\":\"20.99\",\"startTime\":\"21\","
    		+ "\"endTime\":\"11\","
    		+ "\"user\":{\"firstName\":\"John\",\"lastName\":\"Doe\","
    		+ "\"id\":\"428\",\"password\":\"scrum\","
    		+ "\"email\":\"john.doe@mail.mcgill.ca\","
    		+ "\"isRenter\":\"true\",\"isSeller\":\"false\","
    		+ "\"parkingManager\":{\"pkey\":\"1\"}},"
    		+ "\"parkingManager\":{\"pkey\":\"1\"},"
    		+ "\"spot\":{\"pkey\":\"321\",\"addressNumber\":\"1234\","
    		+ "\"streetName\":\"Kennedy\",\"postalCode\":\"H0H 0H0\","
    		+ "\"avgRating\":\"0\",\"currentPrice\":\"20\","
    		+ "\"user\":{\"firstName\":\"John\",\"lastName\":\"Doe\","
    		+ "\"id\":\"428\",\"password\":\"scrum\","
    		+ "\"email\":\"john.doe@mail.mcgill.ca\",\"isRenter\":\"true\","
    		+ "\"isSeller\":\"false\",\"parkingManager\":{\"pkey\":\"1\"}},"
    		+ "\"parkingManager\":{\"pkey\":\"1\"}}}";
    
 
    
    String reservationExpected = "{\"pkey\":\"888\","
    		+ "\"vehicle_Plate\":\"G1G 3A7\","
    		+ "\"start_Date\":\"2020-08-28 21:00:00\","
    		+ "\"end_Date\":\"2020-08-29 11:00:00\","
    		+ "\"price_Paid\":20.99,\"start_Time\":21,\"end_Time\":11,"
    		+ "\"parkingSpot\":{\"pkey\":\"321\",\"street_Number\":1234,"
    		+ "\"street_Name\":\"Kennedy\",\"postal_Code\":\"H0H 0H0\","
    		+ "\"avg_Rating\":0.0,\"current_Price\":20.0,\"reviews\":[]}}";
    
    @Before
    public void setup() throws Exception {
    	ParkingManager pm = new ParkingManager("1");
    	pmr.addManager(pm);
    	
    	mockMvc.perform(post("/user")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(user1))
    			.andDo(print())
    			.andExpect(status().isOk());
    	
    	mockMvc.perform(post("/spot")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(parkingSpot1))
    			.andDo(print())
    			.andExpect(status().isOk());
    }
    
    @After
    public void tearDown() throws Exception {
    	pmr.deleteManager("1");
    	
    }
    
    @Test
    public void addReservationTest() throws Exception {
    	
    	ResultActions result = mockMvc.perform(post("/reservation")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(reservation1))
    			.andDo(print())
    			.andExpect(status().isOk());
    	
    	MvcResult resultBody = result.andReturn();
        assertEquals(reservationExpected, resultBody.getResponse().getContentAsString());
    }
}
