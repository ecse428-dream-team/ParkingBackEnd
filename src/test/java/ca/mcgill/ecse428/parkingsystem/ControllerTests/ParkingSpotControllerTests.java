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
public class ParkingSpotControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ParkingManagerRepository pmr;

    String user1 = "{\"firstName\":\"Firstname1\"," +
            "\"lastName\":\"Lastname1\"," +
            "\"id\":\"id1\"," +
            "\"password\":\"password1\"," +
            "\"email\":\"Firstname1.Lastname1@gmail.com\"," +
            "\"isRenter\":\"true\"," +
            "\"isSeller\":\"false\"," +
            "\"parkingManager\":" +
            " {\"pkey\":\"2\"}}";

    String parkingSpot1 = "{\"addressNumber\" : \"1234\"," +
            "\"streetName\" : \"Kennedy\"," +
            "\"postalCode\" : \"H0H 0H0\"," +
            "\"avgRating\" : \"0\"," +
            "\"currentPrice\" : \"20\"," +
            "\"user\" : {\"firstName\" : \"Firstname1\"," +
            "\"lastName\" : \"Lastname1\"," +
            "\"id\" : \"id1\"," +
            "\"password\" : \"password1\"," +
            "\"email\" : \"Firstname1.Lastname1@gmail.com\"," +
            "\"isRenter\" : \"true\"," +
            "\"isSeller\" : \"false\"," +
            " \"parkingManager\" : {\"pkey\" : \"2\"}}," +
            "\"parkingManager\" : {\"pkey\" : \"2\"}}";

    String parkingSpot2 = "{\"addressNumber\" : \"5678\"," +
            "\"streetName\" : \"Sherbrooke\"," +
            "\"postalCode\" : \"A1B 2C3\"," +
            "\"avgRating\" : \"0\"," +
            "\"currentPrice\" : \"20\"," +
            "\"user\" : {\"firstName\" : \"Firstname1\"," +
            "\"lastName\" : \"Lastname1\"," +
            "\"id\" : \"id1\"," +
            "\"password\" : \"password1\"," +
            "\"email\" : \"Firstname1.Lastname1@gmail.com\"," +
            "\"isRenter\" : \"true\"," +
            "\"isSeller\" : \"false\", " +
            "\"parkingManager\" : {\"pkey\" : \"2\"}}," +
            "\"parkingManager\" : {\"pkey\" : \"2\"}}";


    @Before
    public void setup() throws Exception {
        ParkingManager pm = new ParkingManager("2");
        pmr.addManager(pm);

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @After
    public void tearDown() throws Exception {
        pmr.deleteManager("2");
        
        
    }
    
    @Test
    public void test16_addParkingSpotTest() throws Exception {
    	// spot pkey = 1;

    	String parkingSpotExpected = "{\"street_Number\":1234,"
        		+ "\"street_Name\":\"Kennedy\",\"postal_Code\":\"H0H 0H0\","
        		+ "\"avg_Rating\":0.0,\"current_Price\":20.0,\"reviews\":[],\"pkey\":1}";
    	
        ResultActions result = mockMvc.perform(post("/spot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingSpot1))
                .andDo(print())
                .andExpect(status().isOk());
        
        MvcResult resultBody = result.andReturn();
        assertEquals(parkingSpotExpected, resultBody.getResponse().getContentAsString());
    }
    
    @Test
    public void test17_getAllParkingSpotsTest() throws Exception {

    	String allParkingSpotsExpected = "[{\"street_Number\":1234,"
        		+ "\"street_Name\":\"Kennedy\",\"postal_Code\":\"H0H 0H0\","
        		+ "\"avg_Rating\":0.0,\"current_Price\":20.0,\"reviews\":[],"
        		+ "\"pkey\":2},{\"street_Number\":5678,\"street_Name\":\"Sherbrooke\","
        		+ "\"postal_Code\":\"A1B 2C3\",\"avg_Rating\":0.0,\"current_Price\":20.0,"
        		+ "\"reviews\":[],\"pkey\":3}]";
    	
        mockMvc.perform(post("/spot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingSpot1))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(post("/spot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingSpot2))
                .andDo(print())
                .andExpect(status().isOk());

        ResultActions getAllResult = mockMvc.perform(get("/spot/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        
        MvcResult resultBody = getAllResult.andReturn();
        assertEquals(allParkingSpotsExpected, resultBody.getResponse().getContentAsString());
    }
    
    @Test
    public void test18_getParkingSpotByIDTest() throws Exception {

    	String parkingSpotExpected = "{\"street_Number\":1234,"
        		+ "\"street_Name\":\"Kennedy\",\"postal_Code\":\"H0H 0H0\","
        		+ "\"avg_Rating\":0.0,\"current_Price\":20.0,\"reviews\":[],\"pkey\":4}";
    	
        mockMvc.perform(post("/spot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingSpot1))
                .andDo(print())
                .andExpect(status().isOk());

        ResultActions getByIDResult = mockMvc.perform(get("/spot/id/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        
        MvcResult resultBody = getByIDResult.andReturn();
        assertEquals(parkingSpotExpected, resultBody.getResponse().getContentAsString());
    }
}

