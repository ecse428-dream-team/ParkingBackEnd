package ca.mcgill.ecse428.parkingsystem.ControllerTests;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ca.mcgill.ecse428.parkingsystem.repository.ParkingManagerRepository;
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

    @Autowired
    ParkingManagerRepository pmr;

    String managerExpected = "{\"pkey\":\"1\"," +
            "\"users\":[]," +
            "\"admins\":[]," +
            "\"parkingSpots\":[]," +
            "\"reservations\":[]," +
            "\"reviews\":[]}";

    String allManagersExpected = "[{\"pkey\":\"1\"," +
            "\"users\":[],\"admins\":[]," +
            "\"parkingSpots\":[],\"reservations\":[]," +
            "\"reviews\":[]}]";
    
    
    @Test
    public void getAllManagersTest() throws Exception {

    	ResultActions getAllResult = mockMvc.perform(get("/manager/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    	
    	MvcResult resultBody = getAllResult.andReturn();
        assertEquals(allManagersExpected, resultBody.getResponse().getContentAsString());
    }
    
    @Test
    public void getManagerByIDTest() throws Exception {

    	ResultActions getByIDResult = mockMvc.perform(get("/manager/id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    	
    	MvcResult resultBody = getByIDResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(managerExpected));
    }

}
