package ca.mcgill.ecse428.parkingsystem.ControllerTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import ca.mcgill.ecse428.parkingsystem.model.ParkingManager;
import ca.mcgill.ecse428.parkingsystem.repository.ParkingManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)		// only works for JUnit 4 // HSA
public class AdminControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParkingManagerRepository pmr;

    String pm = "{\"pkey\":2}";

    String admin1 = "{\"firstName\":\"Firstname1\"," +
            "\"lastName\":\"Lastname1\"," +
            "\"id\":\"id1\"," +
            "\"password\":\"password1\"," +
            "\"email\":\"Firstname1.Lastname1@gmail.com\"," +
            "\"parkingManager\":" +
            " {\"pkey\":\"2\"}}";

    String admin2 = "{\"firstName\":\"Firstname2\"," +
            "\"lastName\":\"Lastname2\"," +
            "\"id\":\"id2\"," +
            "\"password\":\"password2\"," +
            "\"email\":\"Firstname2.Lastname2@gmail.com\"," +
            "\"parkingManager\":" +
            " {\"pkey\":\"2\"}}";

    String admin1Expected = "{\"password\":\"password1\"," +
            "\"email\":\"Firstname1.Lastname1@gmail.com\"," +
            "\"first_name\":\"Firstname1\"," +
            "\"last_Name\":\"Lastname1\"," +
            "\"userID\":\"id1\"}";

    String getAllAdminsExpected = "[{\"password\":\"password1\"," +
            "\"email\":\"Firstname1.Lastname1@gmail.com\"," +
            "\"first_name\":\"Firstname1\"," +
            "\"last_Name\":\"Lastname1\"," +
            "\"userID\":\"id1\"}," +
            "{\"password\":\"password2\"," +
            "\"email\":\"Firstname2.Lastname2@gmail.com\"," +
            "\"first_name\":\"Firstname2\"," +
            "\"last_Name\":\"Lastname2\"," +
            "\"userID\":\"id2\"}]";

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
    public void test13_addAdminTest() throws Exception {

    	ResultActions result = mockMvc.perform(post("/admin")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(admin1))
    			.andDo(print())
    			.andExpect(status().isOk());

    	MvcResult resultBody = result.andReturn();
    	assertEquals(admin1Expected, resultBody.getResponse().getContentAsString());
    }
    
    @Test
    public void test14_getAllAdminsTest() throws Exception {

        mockMvc.perform(post("/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(admin1))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(post("/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(admin2))
                .andDo(print())
                .andExpect(status().isOk());

        ResultActions getAllResult = mockMvc.perform(get("/admin/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        
        MvcResult resultBody = getAllResult.andReturn();
        assertEquals(getAllAdminsExpected, resultBody.getResponse().getContentAsString());
    }
    
    @Test
    public void test15_getAdminByIDTest() throws Exception {

        mockMvc.perform(post("/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(admin1))
                .andDo(print())
                .andExpect(status().isOk());

        ResultActions getByIDResult = mockMvc.perform(get("/admin/id/id1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        
        MvcResult resultBody = getByIDResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(admin1Expected));
    }
    
}
