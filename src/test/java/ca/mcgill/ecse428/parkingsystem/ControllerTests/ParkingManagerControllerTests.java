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
            "\"reviews\":[]},{\"pkey\":\"2\",\"users\":[],"
            + "\"admins\":[],\"parkingSpots\":[],\"reservations\":[],"
            + "\"reviews\":[]}]";
    

    @Before
    public void setup() throws Exception {
        ParkingManager pm = new ParkingManager("2");
        pmr.addManager(pm);
    }

    @After
    public void tearDown() throws Exception {
        pmr.deleteManager("2");
    }

    
    @Test
    public void test01_getAllManagersTest() throws Exception {

    	ResultActions getAllResult = mockMvc.perform(get("/manager/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    	
    	MvcResult resultBody = getAllResult.andReturn();
        assertEquals(allManagersExpected, resultBody.getResponse().getContentAsString());
    }
    
    @Test
    public void test02_getManagerByIDTest() throws Exception {

    	ResultActions getByIDResult = mockMvc.perform(get("/manager/id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    	
    	MvcResult resultBody = getByIDResult.andReturn();
        assertEquals(managerExpected, resultBody.getResponse().getContentAsString());
    }

}
