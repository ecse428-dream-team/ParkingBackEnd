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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)		// only works for JUnit 4 // HSA
public class ReservationControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ParkingManagerRepository pmr;
	

    String user1 = "{\"firstName\":\"John\","
    		+ "\"lastName\":\"Doe\",\"id\":\"428\","
    		+ "\"password\":\"scrum\",\"email\":\"john.doe@mail.mcgill.ca\","
    		+ "\"isRenter\":\"true\",\"isSeller\":\"false\","
    		+ "\"parkingManager\":{\"pkey\":\"2\"}}";
    
    String parkingSpot1 = "{\"addressNumber\":\"1234\","
    		+ "\"streetName\":\"Kennedy\",\"postalCode\":\"H0H 0H0\","
    		+ "\"avgRating\":\"0\",\"currentPrice\":\"20\","
    		+ "\"user\":{\"firstName\":\"John\",\"lastName\":\"Doe\","
    		+ "\"id\":\"428\",\"password\":\"scrum\","
    		+ "\"email\":\"john.doe@mail.mcgill.ca\","
    		+ "\"isRenter\":\"true\",\"isSeller\":\"false\","
    		+ "\"parkingManager\":{\"pkey\":\"2\"}},"
    		+ "\"parkingManager\":{\"pkey\":\"2\"}}";
    
    
    @Before
    public void setup() throws Exception {
    	ParkingManager pm = new ParkingManager("2");
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
    	pmr.deleteManager("2");
    	
    }
    
    @Test
    public void addReservationTest() throws Exception {
    	// parking spot pkey = 1;
    	
    	 String reservation1 = "{\"plate\":\"G1G 3A7\","
    	    		+ "\"startDate\":\"2020-08-28 21:00:00\","
    	    		+ "\"endDate\":\"2020-08-29 11:00:00\","
    	    		+ "\"pricePaid\":\"20.99\",\"startTime\":\"21\","
    	    		+ "\"endTime\":\"11\",\"user\":{\"firstName\":"
    	    		+ "\"John\",\"lastName\":\"Doe\",\"id\":\"428\","
    	    		+ "\"password\":\"scrum\",\"email\":\"john.doe@mail.mcgill.ca\","
    	    		+ "\"isRenter\":\"true\",\"isSeller\":\"false\","
    	    		+ "\"parkingManager\":{\"pkey\":\"2\"}},"
    	    		+ "\"parkingManager\":{\"pkey\":\"2\"},\"spot\":{\"pkey\":\"1\","
    	    		+ "\"addressNumber\":\"1234\",\"streetName\":\"Kennedy\","
    	    		+ "\"postalCode\":\"H0H 0H0\",\"avgRating\":\"0\","
    	    		+ "\"currentPrice\":\"20\",\"user\":{\"firstName\":\"John\","
    	    		+ "\"lastName\":\"Doe\",\"id\":\"428\",\"password\":\"scrum\","
    	    		+ "\"email\":\"john.doe@mail.mcgill.ca\",\"isRenter\":\"true\","
    	    		+ "\"isSeller\":\"false\",\"parkingManager\":{\"pkey\":\"2\"}},"
    	    		+ "\"parkingManager\":{\"pkey\":\"2\"}}}";
    	    
    	    String reservation2 = "{\"plate\":\"A1A 2B2\","
    	    		+ "\"startDate\":\"2020-09-01 11:00:00\","
    	    		+ "\"endDate\":\"2020-09-03 14:00:00\","
    	    		+ "\"pricePaid\":\"20.99\",\"startTime\":\"21\","
    	    		+ "\"endTime\":\"11\",\"user\":{\"firstName\":"
    	    		+ "\"John\",\"lastName\":\"Doe\",\"id\":\"428\","
    	    		+ "\"password\":\"scrum\",\"email\":\"john.doe@mail.mcgill.ca\","
    	    		+ "\"isRenter\":\"true\",\"isSeller\":\"false\","
    	    		+ "\"parkingManager\":{\"pkey\":\"2\"}},"
    	    		+ "\"parkingManager\":{\"pkey\":\"2\"},\"spot\":{\"pkey\":\"1\","
    	    		+ "\"addressNumber\":\"1234\",\"streetName\":\"Kennedy\","
    	    		+ "\"postalCode\":\"H0H 0H0\",\"avgRating\":\"0\","
    	    		+ "\"currentPrice\":\"20\",\"user\":{\"firstName\":\"John\","
    	    		+ "\"lastName\":\"Doe\",\"id\":\"428\",\"password\":\"scrum\","
    	    		+ "\"email\":\"john.doe@mail.mcgill.ca\",\"isRenter\":\"true\","
    	    		+ "\"isSeller\":\"false\",\"parkingManager\":{\"pkey\":\"2\"}},"
    	    		+ "\"parkingManager\":{\"pkey\":\"2\"}}}";
    	    
    	    
    	    String reservationExpected = "{\"vehicle_Plate\":\"G1G 3A7\","
    	    		+ "\"start_Date\":\"2020-08-28 21:00:00\","
    	    		+ "\"end_Date\":\"2020-08-29 11:00:00\","
    	    		+ "\"price_Paid\":20.99,\"start_Time\":21,\"end_Time\":11,"
    	    		+ "\"parkingSpot\":{\"street_Number\":1234,"
    	    		+ "\"street_Name\":\"Kennedy\",\"postal_Code\":\"H0H 0H0\","
    	    		+ "\"avg_Rating\":0.0,\"current_Price\":20.0,"
    	    		+ "\"reviews\":[],\"pkey\":1},\"pkey\":2}";
    	    
    	ResultActions result = mockMvc.perform(post("/reservation")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(reservation1))
    			.andDo(print())
    			.andExpect(status().isOk());
    	
    	MvcResult resultBody = result.andReturn();
        assertEquals(reservationExpected, resultBody.getResponse().getContentAsString());
        
    }
    
    @Test
    public void getAllReservationsTest() throws Exception{
    	//Parking Spot pkey = 3
    	
    	String reservation1 = "{\"plate\":\"G1G 3A7\","
        		+ "\"startDate\":\"2020-08-28 21:00:00\","
        		+ "\"endDate\":\"2020-08-29 11:00:00\","
        		+ "\"pricePaid\":\"20.99\",\"startTime\":\"21\","
        		+ "\"endTime\":\"11\",\"user\":{\"firstName\":"
        		+ "\"John\",\"lastName\":\"Doe\",\"id\":\"428\","
        		+ "\"password\":\"scrum\",\"email\":\"john.doe@mail.mcgill.ca\","
        		+ "\"isRenter\":\"true\",\"isSeller\":\"false\","
        		+ "\"parkingManager\":{\"pkey\":\"2\"}},"
        		+ "\"parkingManager\":{\"pkey\":\"2\"},\"spot\":{\"pkey\":\"3\","
        		+ "\"addressNumber\":\"1234\",\"streetName\":\"Kennedy\","
        		+ "\"postalCode\":\"H0H 0H0\",\"avgRating\":\"0\","
        		+ "\"currentPrice\":\"20\",\"user\":{\"firstName\":\"John\","
        		+ "\"lastName\":\"Doe\",\"id\":\"428\",\"password\":\"scrum\","
        		+ "\"email\":\"john.doe@mail.mcgill.ca\",\"isRenter\":\"true\","
        		+ "\"isSeller\":\"false\",\"parkingManager\":{\"pkey\":\"2\"}},"
        		+ "\"parkingManager\":{\"pkey\":\"2\"}}}";
    	
    	String reservation2 = "{\"plate\":\"A1A 2B2\","
        		+ "\"startDate\":\"2020-09-01 11:00:00\","
        		+ "\"endDate\":\"2020-09-03 14:00:00\","
        		+ "\"pricePaid\":\"20.99\",\"startTime\":\"21\","
        		+ "\"endTime\":\"11\",\"user\":{\"firstName\":"
        		+ "\"John\",\"lastName\":\"Doe\",\"id\":\"428\","
        		+ "\"password\":\"scrum\",\"email\":\"john.doe@mail.mcgill.ca\","
        		+ "\"isRenter\":\"true\",\"isSeller\":\"false\","
        		+ "\"parkingManager\":{\"pkey\":\"2\"}},"
        		+ "\"parkingManager\":{\"pkey\":\"2\"},\"spot\":{\"pkey\":\"3\","
        		+ "\"addressNumber\":\"1234\",\"streetName\":\"Kennedy\","
        		+ "\"postalCode\":\"H0H 0H0\",\"avgRating\":\"0\","
        		+ "\"currentPrice\":\"20\",\"user\":{\"firstName\":\"John\","
        		+ "\"lastName\":\"Doe\",\"id\":\"428\",\"password\":\"scrum\","
        		+ "\"email\":\"john.doe@mail.mcgill.ca\",\"isRenter\":\"true\","
        		+ "\"isSeller\":\"false\",\"parkingManager\":{\"pkey\":\"2\"}},"
        		+ "\"parkingManager\":{\"pkey\":\"2\"}}}";
    	
    	String allReservationsExpected = "[{\"vehicle_Plate\":\"G1G 3A7\","
    			+ "\"start_Date\":\"2020-08-28 04:00:00\","
    			+ "\"end_Date\":\"2020-08-29 04:00:00\","
    			+ "\"price_Paid\":20.99,\"start_Time\":21,"
    			+ "\"end_Time\":11,\"parkingSpot\":{\"street_Number\":1234,"
    			+ "\"street_Name\":\"Kennedy\",\"postal_Code\":\"H0H 0H0\","
    			+ "\"avg_Rating\":0.0,\"current_Price\":20.0,\"reviews\":[],"
    			+ "\"pkey\":3,\"hibernateLazyInitializer\":{}},\"pkey\":4},"
    			+ "{\"vehicle_Plate\":\"A1A 2B2\",\"start_Date\":\"2020-09-01 04:00:00\","
    			+ "\"end_Date\":\"2020-09-03 04:00:00\",\"price_Paid\":20.99,"
    			+ "\"start_Time\":21,\"end_Time\":11,\"parkingSpot\":{\"street_Number\":1234,"
    			+ "\"street_Name\":\"Kennedy\",\"postal_Code\":\"H0H 0H0\","
    			+ "\"avg_Rating\":0.0,\"current_Price\":20.0,\"reviews\":[],"
    			+ "\"pkey\":3,\"hibernateLazyInitializer\":{}},\"pkey\":5}]";
    	
    	mockMvc.perform(post("/reservation")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(reservation1))
    			.andDo(print())
    			.andExpect(status().isOk());
    	
    	mockMvc.perform(post("/reservation")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(reservation2))
    			.andDo(print())
    			.andExpect(status().isOk());
    	
    	ResultActions getAllResult = mockMvc.perform(get("/reservation/all")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andDo(print());
    	
    	MvcResult resultBody = getAllResult.andReturn();
        assertEquals(allReservationsExpected, resultBody.getResponse().getContentAsString());
    
    }
    
    
    @Test
    public void getReservationByIDTest() throws Exception {
    	// parking spot pkey = 6;
    	
    	 String reservation1 = "{\"plate\":\"G1G 3A7\","
    	    		+ "\"startDate\":\"2020-08-28 21:00:00\","
    	    		+ "\"endDate\":\"2020-08-29 11:00:00\","
    	    		+ "\"pricePaid\":\"20.99\",\"startTime\":\"21\","
    	    		+ "\"endTime\":\"11\",\"user\":{\"firstName\":"
    	    		+ "\"John\",\"lastName\":\"Doe\",\"id\":\"428\","
    	    		+ "\"password\":\"scrum\",\"email\":\"john.doe@mail.mcgill.ca\","
    	    		+ "\"isRenter\":\"true\",\"isSeller\":\"false\","
    	    		+ "\"parkingManager\":{\"pkey\":\"2\"}},"
    	    		+ "\"parkingManager\":{\"pkey\":\"2\"},\"spot\":{\"pkey\":\"6\","
    	    		+ "\"addressNumber\":\"1234\",\"streetName\":\"Kennedy\","
    	    		+ "\"postalCode\":\"H0H 0H0\",\"avgRating\":\"0\","
    	    		+ "\"currentPrice\":\"20\",\"user\":{\"firstName\":\"John\","
    	    		+ "\"lastName\":\"Doe\",\"id\":\"428\",\"password\":\"scrum\","
    	    		+ "\"email\":\"john.doe@mail.mcgill.ca\",\"isRenter\":\"true\","
    	    		+ "\"isSeller\":\"false\",\"parkingManager\":{\"pkey\":\"2\"}},"
    	    		+ "\"parkingManager\":{\"pkey\":\"2\"}}}";
    	    
    	    String reservationExpected = "{\"vehicle_Plate\":\"G1G 3A7\","
    	    		+ "\"start_Date\":\"2020-08-28 21:00:00\","
    	    		+ "\"end_Date\":\"2020-08-29 11:00:00\","
    	    		+ "\"price_Paid\":20.99,\"start_Time\":21,\"end_Time\":11,"
    	    		+ "\"parkingSpot\":{\"street_Number\":1234,"
    	    		+ "\"street_Name\":\"Kennedy\",\"postal_Code\":\"H0H 0H0\","
    	    		+ "\"avg_Rating\":0.0,\"current_Price\":20.0,"
    	    		+ "\"reviews\":[],\"pkey\":1},\"pkey\":2}";
    	    
    	mockMvc.perform(post("/reservation")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(reservation1))
    			.andDo(print())
    			.andExpect(status().isOk());
    	
    	ResultActions getByIDResult = mockMvc.perform(get("/reservation/id/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    	
    	MvcResult resultBody = getByIDResult.andReturn();
        assertEquals(reservationExpected, resultBody.getResponse().getContentAsString());
        
       
    }
}
