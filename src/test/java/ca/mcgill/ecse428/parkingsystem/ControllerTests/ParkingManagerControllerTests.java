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
public class ParkingManagerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHello() throws Exception {
        ResultActions result = mockMvc.perform(get("/manager"))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult resultBody = result.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals("Hello"));
    }
    
    @Test
    public void addManagerTest() throws Exception {
    	
    	String manager = "{\"pkey\":1}";
    	
    	ResultActions result = mockMvc.perform(post("/manager")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(manager))
    			.andDo(print())
    			.andExpect(status().isOk());
    	
    	String resultString = "{\"pkey\":\"1\"," +
    			"\"users\":[],\"admins\":[]," + 
    			"\"parkingSpots\":[],\"reservations\":[]," + 
    			"\"reviews\":[]}";
    	
    	MvcResult resultBody = result.andReturn();
    	assertTrue(resultBody.getResponse().getContentAsString().equals(resultString));
    }
    
    @Test
    public void getAllManagersTest() throws Exception {
    	
    	String manager_1 = "{\"pkey\":1}";
    	
    	mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(manager_1))
                .andDo(print())
                .andExpect(status().isOk());
    	
    	String manager_2 = "{\"pkey\":2}";
    	
    	mockMvc.perform(post("/manager")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(manager_2))
    			.andDo(print())
    			.andExpect(status().isOk());
    	
    	String result_managers = "{\"pkey\":\"1\"," +
    			"\"users\":[],\"admins\":[]," + 
    			"\"parkingSpots\":[],\"reservations\":[]," + 
    			"\"reviews\":[], \"pkey\":\"2\"," +
    			"\"users\":[],\"admins\":[]," + 
    			"\"parkingSpots\":[],\"reservations\":[]," + 
    			"\"reviews\":[]}";
    	
    	ResultActions getAllResult = mockMvc.perform(get("/manager/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    	
    	MvcResult resultBody = getAllResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(result_managers));
    }
    
    @Test
    public void getManagerByIDTest() throws Exception {
    	
    	String manager_1 = "{\"pkey\":1}";
    	
    	mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(manager_1))
                .andDo(print())
                .andExpect(status().isOk());
    	
    	String resultString = "{\"pkey\":\"1\"," +
    			"\"users\":[],\"admins\":[]," + 
    			"\"parkingSpots\":[],\"reservations\":[]," + 
    			"\"reviews\":[]}";
    	
    	ResultActions getByIDResult = mockMvc.perform(get("/manager/id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    	
    	MvcResult resultBody = getByIDResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(resultString));
    }

}
