package ca.mcgill.ecse428.parkingsystem.ControllerTests;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class ParkingSpotControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHello() throws Exception {
        ResultActions result = mockMvc.perform(get("/spot"))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult resultBody = result.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals("Hello"));
    }
    
    @Test
    public void addParkingSpotTest() throws Exception {
    	
    	String pm = "{\"pkey\":1}";

        mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pm))
                .andDo(print());
        

        String user = "{\"firstName\":\"HyunSu\"," +
                "\"lastName\":\"An\"," +
                "\"id\":\"260775639\"," +
                "\"password\":\"hi\"," +
                "\"email\":\"1234@gmail.com\"," +
                "\"isRenter\":\"true\"," +
                "\"isSeller\":\"false\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"1\"}}";
        
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user))
                .andDo(print());
        
        String parkingSpot = "{\"pkey\" : \"321\",\"addressNumber\" : \"1234\","
        		+ "\"streetName\" : \"Kennedy\",\"postalCode\" : \"H0H 0H0\","
        		+ "\"avgRating\" : \"0\",\"currentPrice\" : \"20\","
        		+ "\"user\" : {\"firstName\" : \"HyunSu\",\"lastName\" : \"An\","
        		+ "\"id\" : \"260775639\",\"password\" : \"hi\","
        		+ "\"email\" : \"1234@gmail.com\",\"isRenter\" : \"true\","
        		+ "\"isSeller\" : \"false\", \"parkingManager\" : {\"pkey\" : \"1\"}},"
        		+ "\"parkingManager\" : {\"pkey\" : \"1\"}}";
        
        ResultActions result = mockMvc.perform(post("/spot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingSpot))
                .andDo(print())
                .andExpect(status().isOk());
        
        String resultString = "{\"pkey\":\"321\",\"street_Number\":1234," + 
        		"\"steet_Name\":\"Kennedy\",\"postal_Code\":\"H0H 0H0\",\"avg_Rating\":0.0," + 
        		"\"current_Price\":20.0,\"reviews\":[]}";
        
        MvcResult resultBody = result.andReturn();
        assertTrue(resultBody.getResponse().getContentAsString().equals(resultString));
    }
    
    @Test
    public void getAllParkingSpotsTest() throws Exception {
    	
    	String pm = "{\"pkey\":1}";

        mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pm))
                .andDo(print());
        

        String user = "{\"firstName\":\"HyunSu\"," +
                "\"lastName\":\"An\"," +
                "\"id\":\"260775639\"," +
                "\"password\":\"hi\"," +
                "\"email\":\"1234@gmail.com\"," +
                "\"isRenter\":\"true\"," +
                "\"isSeller\":\"false\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"1\"}}";
        
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user))
                .andDo(print());
        
        String parkingSpot_1 = "{\"pkey\" : \"321\",\"addressNumber\" : \"1234\","
        		+ "\"streetName\" : \"Kennedy\",\"postalCode\" : \"H0H 0H0\","
        		+ "\"avgRating\" : \"0\",\"currentPrice\" : \"20\","
        		+ "\"user\" : {\"firstName\" : \"HyunSu\",\"lastName\" : \"An\","
        		+ "\"id\" : \"260775639\",\"password\" : \"hi\","
        		+ "\"email\" : \"1234@gmail.com\",\"isRenter\" : \"true\","
        		+ "\"isSeller\" : \"false\", \"parkingManager\" : {\"pkey\" : \"1\"}},"
        		+ "\"parkingManager\" : {\"pkey\" : \"1\"}}";
        
        mockMvc.perform(post("/spot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingSpot_1))
                .andDo(print())
                .andExpect(status().isOk());
        
        String parkingSpot_2 = "{\"pkey\" : \"876\",\"addressNumber\" : \"5678\","
        		+ "\"streetName\" : \"Sherbrooke\",\"postalCode\" : \"A1B 2C3\","
        		+ "\"avgRating\" : \"0\",\"currentPrice\" : \"20\","
        		+ "\"user\" : {\"firstName\" : \"HyunSu\",\"lastName\" : \"An\","
        		+ "\"id\" : \"260775639\",\"password\" : \"hi\","
        		+ "\"email\" : \"1234@gmail.com\",\"isRenter\" : \"true\","
        		+ "\"isSeller\" : \"false\", \"parkingManager\" : {\"pkey\" : \"1\"}},"
        		+ "\"parkingManager\" : {\"pkey\" : \"1\"}}";
        
        mockMvc.perform(post("/spot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingSpot_2))
                .andDo(print())
                .andExpect(status().isOk());
        
        String result_ParkingSpots = "{\"pkey\":\"321\",\"street_Number\":1234," + 
        		"\"steet_Name\":\"Kennedy\",\"postal_Code\":\"H0H 0H0\",\"avg_Rating\":0.0," + 
        		"\"current_Price\":20.0,\"reviews\":[]," +
        		"\"pkey\":\"876\",\"street_Number\":5678," + 
        		"\"steet_Name\":\"Sherbrooke\",\"postal_Code\":\"A1B 2C3\",\"avg_Rating\":0.0," + 
        		"\"current_Price\":20.0,\"reviews\":[]}";
        
        ResultActions getAllResult = mockMvc.perform(get("/spot/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        
        MvcResult resultBody = getAllResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(result_ParkingSpots));
    }
    
    @Test
    public void getParkingSpotByIDTest() throws Exception {
    	
    	String pm = "{\"pkey\":1}";

        mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pm))
                .andDo(print());
        

        String user = "{\"firstName\":\"HyunSu\"," +
                "\"lastName\":\"An\"," +
                "\"id\":\"260775639\"," +
                "\"password\":\"hi\"," +
                "\"email\":\"1234@gmail.com\"," +
                "\"isRenter\":\"true\"," +
                "\"isSeller\":\"false\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"1\"}}";
        
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user))
                .andDo(print());
        
        String parkingSpot = "{\"pkey\" : \"321\",\"addressNumber\" : \"1234\","
        		+ "\"streetName\" : \"Kennedy\",\"postalCode\" : \"H0H 0H0\","
        		+ "\"avgRating\" : \"0\",\"currentPrice\" : \"20\","
        		+ "\"user\" : {\"firstName\" : \"HyunSu\",\"lastName\" : \"An\","
        		+ "\"id\" : \"260775639\",\"password\" : \"hi\","
        		+ "\"email\" : \"1234@gmail.com\",\"isRenter\" : \"true\","
        		+ "\"isSeller\" : \"false\", \"parkingManager\" : {\"pkey\" : \"1\"}},"
        		+ "\"parkingManager\" : {\"pkey\" : \"1\"}}";
        
        mockMvc.perform(post("/spot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parkingSpot))
                .andDo(print())
                .andExpect(status().isOk());
        
        String resultString = "{\"pkey\":\"321\",\"street_Number\":1234," + 
        		"\"steet_Name\":\"Kennedy\",\"postal_Code\":\"H0H 0H0\",\"avg_Rating\":0.0," + 
        		"\"current_Price\":20.0,\"reviews\":[]}";
        
        ResultActions getByIDResult = mockMvc.perform(get("/spot/id/321")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        
        MvcResult resultBody = getByIDResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(resultString));
    }
}
