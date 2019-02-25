package ca.mcgill.ecse428.parkingsystem.ControllerTests;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
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

    String manager1 = "{\"pkey\":1}";

    String manager2 = "{\"pkey\":2}";

    String managerExpected = "{\"pkey\":\"1\"," +
            "\"users\":[]," +
            "\"admins\":[]," +
            "\"parkingSpots\":[]," +
            "\"reservations\":[]," +
            "\"reviews\":[]}";

    String allManagersExpected = "{\"pkey\":\"1\"," +
            "\"users\":[],\"admins\":[]," +
            "\"parkingSpots\":[],\"reservations\":[]," +
            "\"reviews\":[], \"pkey\":\"2\"," +
            "\"users\":[],\"admins\":[]," +
            "\"parkingSpots\":[],\"reservations\":[]," +
            "\"reviews\":[]}";

    @After
    public void tearDown() throws Exception {
        mockMvc.perform(delete("/manager/pkey/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void addManagerTest() throws Exception {

    	ResultActions result = mockMvc.perform(post("/manager")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(manager1))
    			.andDo(print())
    			.andExpect(status().isOk());

    	MvcResult resultBody = result.andReturn();
    	assertTrue(resultBody.getResponse().getContentAsString().equals(managerExpected));
    }
    
    @Test
    public void getAllManagersTest() throws Exception {
    	
    	mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(manager1))
                .andDo(print())
                .andExpect(status().isOk());
    	
    	mockMvc.perform(post("/manager")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(manager2))
    			.andDo(print())
    			.andExpect(status().isOk());

    	ResultActions getAllResult = mockMvc.perform(get("/manager/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    	
    	MvcResult resultBody = getAllResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(allManagersExpected));
    }
    
    @Test
    public void getManagerByIDTest() throws Exception {
    	
    	mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(manager1))
                .andDo(print())
                .andExpect(status().isOk());
    	
    	ResultActions getByIDResult = mockMvc.perform(get("/manager/id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    	
    	MvcResult resultBody = getByIDResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(managerExpected));
    }

}
