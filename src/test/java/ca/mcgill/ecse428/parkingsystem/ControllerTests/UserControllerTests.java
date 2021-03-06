package ca.mcgill.ecse428.parkingsystem.ControllerTests;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;

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
public class UserControllerTests {

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

    String user2 = "{\"firstName\":\"Firstname2\"," +
            "\"lastName\":\"Lastname2\"," +
            "\"id\":\"id2\"," +
            "\"password\":\"password2\"," +
            "\"email\":\"Firstname2.Lastname2@gmail.com\"," +
            "\"isRenter\":\"false\"," +
            "\"isSeller\":\"true\"," +
            "\"parkingManager\":" +
            " {\"pkey\":\"2\"}}";

    String user1Expected = "[{\"password\":\"password1\"," +
            "\"email\":\"Firstname1.Lastname1@gmail.com\"," +
            "\"isRenter\":true," +
            "\"isSeller\":false," +
            "\"first_name\":\"Firstname1\"," +
            "\"last_Name\":\"Lastname1\"," +
            "\"userID\":\"id1\"," +
            "\"parkingSpots\":[],\"reservations\":[]}]";
    

    String user2Expected = "[{\"password\":\"password2\"," +
            "\"email\":\"Firstname2.Lastname2@gmail.com\"," +
            "\"isRenter\":false," +
            "\"isSeller\":true," +
            "\"first_name\":\"Firstname2\"," +
            "\"last_Name\":\"Lastname2\"," +
            "\"userID\":\"id2\"," +
            "\"parkingSpots\":[],\"reservations\":[]}]";

    String getAllUsersExpected = "[{\"password\":\"password1\","
    		+ "\"email\":\"Firstname1.Lastname1@gmail.com\","
    		+ "\"isRenter\":true,\"isSeller\":false,"
    		+ "\"first_name\":\"Firstname1\",\"last_Name\":\"Lastname1\","
    		+ "\"userID\":\"id1\",\"parkingSpots\":[],"
    		+ "\"reservations\":[]},"
    		+ "{\"password\":\"password2\","
    		+ "\"email\":\"Firstname2.Lastname2@gmail.com\","
    		+ "\"isRenter\":false,\"isSeller\":true,"
    		+ "\"first_name\":\"Firstname2\",\"last_Name\":\"Lastname2\","
    		+ "\"userID\":\"id2\",\"parkingSpots\":[],\"reservations\":[]}]";

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
    public void test03_addRenterTest() throws Exception {

        ResultActions result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1))
                .andDo(print())
                .andExpect(status().isOk());

        String resultString = "User Created";


        MvcResult resultBody = result.andReturn();
        assertEquals(resultString, resultBody.getResponse().getContentAsString());
    }
    
    @Test
    public void test04_addSellerTest() throws Exception {

        ResultActions result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user2))
                .andDo(print())
                .andExpect(status().isOk());

        String resultString = "User Created";

        MvcResult resultBody = result.andReturn();
        assertEquals(resultString, resultBody.getResponse().getContentAsString());
    }

    @Test
    public void test05_getAllUsersTest() throws Exception {

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user2))
                .andDo(print())
                .andExpect(status().isOk());

        // Get all users
        ResultActions getAllResult = mockMvc.perform(get("/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Check if responses match
        MvcResult resultBody = getAllResult.andReturn();
        assertEquals(getAllUsersExpected, resultBody.getResponse().getContentAsString());
    }

    @Test
    public void test06_getUserByFirstNameTest() throws Exception {

        // Post to the local database
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1))
                .andDo(print())
                .andExpect(status().isOk());

        // Get user
        ResultActions getByFirstNameResult = mockMvc.perform(get("/user/firstname/Firstname1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Check if response match
        MvcResult resultBody = getByFirstNameResult.andReturn();
        assertEquals(user1Expected, resultBody.getResponse().getContentAsString());
    }

    @Test
    public void test07_getUserByLastNameTest() throws Exception {

        // Post to the local database
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1))
                .andDo(print())
                .andExpect(status().isOk());

        // Get user
        ResultActions getByLastNameResult = mockMvc.perform(get("/user/lastname/Lastname1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Check if response match
        MvcResult resultBody = getByLastNameResult.andReturn();
        assertEquals(user1Expected, resultBody.getResponse().getContentAsString());
    }

    @Test
    public void test08_getUserByIDTest() throws Exception {

    	
        // Post to the local database
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1))
                .andDo(print())
                .andExpect(status().isOk());

        // Get user
        ResultActions getByIDResult = mockMvc.perform(get("/user/id/id1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Check if response match
        MvcResult resultBody = getByIDResult.andReturn();
        assertEquals(user1Expected.substring(1, user1Expected.length() - 1), resultBody.getResponse().getContentAsString());
    }
    
    @Test
    public void test09_deleteUserById() throws Exception 
    {

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1))
                .andDo(print())
                .andExpect(status().isOk());

        // Delete user
        mockMvc.perform(delete("/user/id/id1"))
                .andDo(print())
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/user/id/id1"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test10_authenticateWithEmail() throws Exception {

        // Post to the local database
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1))
                .andDo(print())
                .andExpect(status().isOk());

        String authorizationHeaderEmail = "Firstname1.Lastname1@gmail.com:password1";
        authorizationHeaderEmail = Base64.getEncoder().encodeToString(authorizationHeaderEmail.getBytes("utf-8"));
        authorizationHeaderEmail = "basic " + authorizationHeaderEmail;

        mockMvc.perform(post("/user/authenticate")
                .header("Authorization", authorizationHeaderEmail)
                .header("Method", "email"))
                .andExpect(status().isOk());

    }

    @Test
    public void test11_authenticateWithUsername() throws Exception {

        // Post to the local database
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1))
                .andDo(print())
                .andExpect(status().isOk());

        String authorizationHeaderUsername = "id1:password1";
        authorizationHeaderUsername = Base64.getEncoder().encodeToString(authorizationHeaderUsername.getBytes("utf-8"));
        authorizationHeaderUsername = "basic " + authorizationHeaderUsername;

        mockMvc.perform(post("/user/authenticate")
                .header("Authorization", authorizationHeaderUsername)
                .header("Method", "username"))
                .andExpect(status().isOk());

    }

    @Test
    public void test12_authenticationFailsOnWrongCredentials() throws Exception {

        // Post to the local database
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1))
                .andDo(print())
                .andExpect(status().isOk());

        String authorizationHeaderWrong = "someemail:somepassword";
        authorizationHeaderWrong = Base64.getEncoder().encodeToString(authorizationHeaderWrong.getBytes("utf-8"));
        authorizationHeaderWrong = "basic " + authorizationHeaderWrong;

        mockMvc.perform(post("/user/authenticate")
                .header("Authorization", authorizationHeaderWrong)
                .header("Method", "username"))
                .andExpect(status().isUnauthorized());

    }
}
