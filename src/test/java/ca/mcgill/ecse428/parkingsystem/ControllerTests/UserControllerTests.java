package ca.mcgill.ecse428.parkingsystem.ControllerTests;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ca.mcgill.ecse428.parkingsystem.model.ParkingManager;
import ca.mcgill.ecse428.parkingsystem.model.User;
import ca.mcgill.ecse428.parkingsystem.repository.UserRepository;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHello() throws Exception {
        ResultActions result = mockMvc.perform(get("/user/hello"))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult resultBody = result.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals("Hello"));
    }

    @Test
    public void addRenterTest() throws Exception {

        String pm = "{\"pkey\":1}";

        mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pm))
                .andDo(print());


        String user = "{\"firstName\":\"Owais\"," +
                "\"lastName\":\"Khan\"," +
                "\"id\":\"260617913\"," +
                "\"password\":\"something\"," +
                "\"email\":\"123@gmail.com\"," +
                "\"isRenter\":\"true\"," +
                "\"isSeller\":\"false\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"1\"}}";

        ResultActions result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user))
                .andDo(print())
                .andExpect(status().isOk());

        String resultString = "User Created";


        MvcResult resultBody = result.andReturn();
        assertTrue(resultBody.getResponse().getContentAsString().equals(resultString));
    }
    
    @Test
    public void addSellerTest() throws Exception {

        String pm = "{\"pkey\":1}";

        mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pm))
                .andDo(print());


        String user = "{\"firstName\":\"Owais\"," +
                "\"lastName\":\"Khan\"," +
                "\"id\":\"260617913\"," +
                "\"password\":\"something\"," +
                "\"email\":\"123@gmail.com\"," +
                "\"isRenter\":\"false\"," +
                "\"isSeller\":\"true\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"1\"}}";

        ResultActions result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user))
                .andDo(print())
                .andExpect(status().isOk());

        String resultString = "User Created";

        /*
        String resultString = "{\"password\":\"something\"," +
                "\"email\":\"123@gmail.com\"," +
                "\"isRenter\":true," +
                "\"isSeller\":false," +
                "\"first_name\":\"Owais\"," +
                "\"last_Name\":\"Khan\"," +
                "\"userID\":\"260617913\"," +
                "\"parkingSpots\":[],\"reservations\":[]}";
        */


        MvcResult resultBody = result.andReturn();
        assertTrue(resultBody.getResponse().getContentAsString().equals(resultString));
    }

    @Test
    public void getAllUsersTest() throws Exception {

        // Parking manager
        String pm = "{\"pkey\":2}";

        // Post the parking manager to the local database
        mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pm))
                .andDo(print());

        // Create 2 users and post them to the local database
        String user1GetAll = "{\"firstName\":\"Tianhan\"," +
                "\"lastName\":\"Jiang\"," +
                "\"id\":\"260795887\"," +
                "\"password\":\"pass\"," +
                "\"email\":\"1@gmail.com\"," +
                "\"isRenter\":\"true\"," +
                "\"isSeller\":\"false\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"2\"}}";

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1GetAll))
                .andDo(print())
                .andExpect(status().isOk());

        String user2GetAll = "{\"firstName\":\"Dave\"," +
                "\"lastName\":\"Khan\"," +
                "\"id\":\"260617915\"," +
                "\"password\":\"password\"," +
                "\"email\":\"2@gmail.com\"," +
                "\"isRenter\":\"true\"," +
                "\"isSeller\":\"false\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"2\"}}";

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user2GetAll))
                .andDo(print())
                .andExpect(status().isOk());

        // The correct result
        String resultGetAllString = "{\"password\":\"pass\"," +
                "\"email\":\"1@gmail.com\"," +
                "\"isRenter\":true," +
                "\"isSeller\":false," +
                "\"first_name\":\"Tianhan\"," +
                "\"last_Name\":\"Jiang\"," +
                "\"userID\":\"260795887\"," +
                "\"parkingSpots\":[],\"reservations\":[]}," +
                "{\"password\":\"password\"," +
                "\"email\":\"2@gmail.com\"," +
                "\"isRenter\":true," +
                "\"isSeller\":false," +
                "\"first_name\":\"Dave\"," +
                "\"last_Name\":\"Khan\"," +
                "\"userID\":\"260617915\"," +
                "\"parkingSpots\":[],\"reservations\":[]}";

        // Get all users
        ResultActions getAllResult = mockMvc.perform(get("/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Check if responses match
        MvcResult resultBody = getAllResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(resultGetAllString));
    }

    @Test
    public void getUserByFirstNameTest() throws Exception {

        // Parking manager
        String pm = "{\"pkey\":3}";

        // Post the parking manager to the local database
        mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pm))
                .andDo(print());

        // Create an user
        String user1FirstName = "{\"firstName\":\"Tianhan\"," +
                "\"lastName\":\"Jiang\"," +
                "\"id\":\"260795887\"," +
                "\"password\":\"pass\"," +
                "\"email\":\"1@gmail.com\"," +
                "\"isRenter\":\"true\"," +
                "\"isSeller\":\"false\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"3\"}}";

        // Post to the local database
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1FirstName))
                .andDo(print())
                .andExpect(status().isOk());

        // The correct result
        String resultFirstName = "{\"password\":\"pass\"," +
                "\"email\":\"1@gmail.com\"," +
                "\"isRenter\":true," +
                "\"isSeller\":false," +
                "\"first_name\":\"Tianhan\"," +
                "\"last_Name\":\"Jiang\"," +
                "\"userID\":\"260795887\"," +
                "\"parkingSpots\":[],\"reservations\":[]}";

        // Get user
        ResultActions getByFirstNameResult = mockMvc.perform(get("/user/firstname/Tianhan")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Check if response match
        MvcResult resultBody = getByFirstNameResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(resultFirstName));
    }

    @Test
    public void getUserByLastNameTest() throws Exception {

        // Parking manager
        String pm = "{\"pkey\":4}";

        // Post the parking manager to the local database
        mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pm))
                .andDo(print());

        // Create an user
        String user1LastName  = "{\"firstName\":\"Tianhan\"," +
                "\"lastName\":\"Jiang\"," +
                "\"id\":\"260795887\"," +
                "\"password\":\"pass\"," +
                "\"email\":\"1@gmail.com\"," +
                "\"isRenter\":\"true\"," +
                "\"isSeller\":\"false\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"4\"}}";

        // Post to the local database
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1LastName))
                .andDo(print())
                .andExpect(status().isOk());

        // The correct result
        String resultLastName = "{\"password\":\"pass\"," +
                "\"email\":\"1@gmail.com\"," +
                "\"isRenter\":true," +
                "\"isSeller\":false," +
                "\"first_name\":\"Tianhan\"," +
                "\"last_Name\":\"Jiang\"," +
                "\"userID\":\"260795887\"," +
                "\"parkingSpots\":[],\"reservations\":[]}";

        // Get user
        ResultActions getByLastNameResult = mockMvc.perform(get("/user/lastname/Jiang")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Check if response match
        MvcResult resultBody = getByLastNameResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(resultLastName));
    }

    @Test
    public void getUserByIDTest() throws Exception {
        // Parking manager
        String pm = "{\"pkey\":5}";

        // Post the parking manager to the local database
        mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pm))
                .andDo(print());

        // Create an user
        String user1ID  = "{\"firstName\":\"Tianhan\"," +
                "\"lastName\":\"Jiang\"," +
                "\"id\":\"260795887\"," +
                "\"password\":\"pass\"," +
                "\"email\":\"1@gmail.com\"," +
                "\"isRenter\":\"true\"," +
                "\"isSeller\":\"false\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"5\"}}";

        // Post to the local database
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1ID))
                .andDo(print())
                .andExpect(status().isOk());

        // The correct result
        String resultID = "{\"password\":\"pass\"," +
                "\"email\":\"1@gmail.com\"," +
                "\"isRenter\":true," +
                "\"isSeller\":false," +
                "\"first_name\":\"Tianhan\"," +
                "\"last_Name\":\"Jiang\"," +
                "\"userID\":\"260795887\"," +
                "\"parkingSpots\":[],\"reservations\":[]}";

        // Get user
        ResultActions getByIDResult = mockMvc.perform(get("/user/id/260795887")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Check if response match
        MvcResult resultBody = getByIDResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(resultID));
    }
    
    @Test
    public void deleteUserById() throws Exception 
    {
        // Parking manager
        String pm = "{\"pkey\":7}";

        // Post the parking manager to the local database
        mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pm))
                .andDo(print());

        String user2ID  = "{\"firstName\":\"John\"," +
                "\"lastName\":\"Doe\"," +
                "\"id\":\"123456789\"," +
                "\"password\":\"pass\"," +
                "\"email\":\"2@gmail.com\"," +
                "\"isRenter\":\"true\"," +
                "\"isSeller\":\"false\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"7\"}}";

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user2ID))
                .andDo(print())
                .andExpect(status().isOk());

        // The correct result
        String resultID = "{\"password\":\"pass\"," +
                "\"email\":\"2@gmail.com\"," +
                "\"isRenter\":true," +
                "\"isSeller\":false," +
                "\"first_name\":\"John\"," +
                "\"last_Name\":\"Doe\"," +
                "\"userID\":\"123456789\"," +
                "\"parkingSpots\":[],\"reservations\":[]}";

        // Get user
        ResultActions getByIDResult = mockMvc.perform(get("/user/id/123456789")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Check if we get the right user
        MvcResult resultBody = getByIDResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(resultID));


        // Delete the user
        mockMvc.perform(MockMvcRequestBuilders
            .delete("/user/id/123456789")
            .contentType(MediaType.APPLICATION_JSON))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void authenticateGoodInputsTest() throws Exception {

        // Parking manager
        String pm = "{\"pkey\":6}";

        // Post the parking manager to the local database
        mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pm))
                .andDo(print());

        // Create an user
        String user1Authenticate  = "{\"firstName\":\"Tianhan\"," +
                "\"lastName\":\"Jiang\"," +
                "\"id\":\"260795887\"," +
                "\"password\":\"pass\"," +
                "\"email\":\"1@gmail.com\"," +
                "\"isRenter\":\"true\"," +
                "\"isSeller\":\"false\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"5\"}}";

        // Post to the local database
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1Authenticate))
                .andDo(print())
                .andExpect(status().isOk());

        // Authenticate user

    }
}
