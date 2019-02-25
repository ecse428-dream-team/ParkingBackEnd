package ca.mcgill.ecse428.parkingsystem.ControllerTests;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
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

import java.util.Base64;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    String user1 = "{\"firstName\":\"Firstname1\"," +
            "\"lastName\":\"Lastname1\"," +
            "\"id\":\"id1\"," +
            "\"password\":\"password1\"," +
            "\"email\":\"Firstname1.Lastname1@gmail.com\"," +
            "\"isRenter\":\"true\"," +
            "\"isSeller\":\"false\"," +
            "\"parkingManager\":" +
            " {\"pkey\":\"1\"}}";

    String user2 = "{\"firstName\":\"Firstname2\"," +
            "\"lastName\":\"Lastname2\"," +
            "\"id\":\"id2\"," +
            "\"password\":\"psasword2\"," +
            "\"email\":\"Firstname2.Lastname2@gmail.com\"," +
            "\"isRenter\":\"false\"," +
            "\"isSeller\":\"true\"," +
            "\"parkingManager\":" +
            " {\"pkey\":\"1\"}}";

    String user1Expected = "{\"password\":\"password1\"," +
            "\"email\":\"Firstname1.Lastname1@gmail.com\"," +
            "\"isRenter\":true," +
            "\"isSeller\":false," +
            "\"first_name\":\"Firstname1\"," +
            "\"last_Name\":\"Lastname1\"," +
            "\"userID\":\"user1\"," +
            "\"parkingSpots\":[],\"reservations\":[]}";

    String user2Expected = "{\"password\":\"password2\"," +
            "\"email\":\"Firstname2.Lastname2@gmail.com\"," +
            "\"isRenter\":false," +
            "\"isSeller\":true," +
            "\"first_name\":\"Firstname2\"," +
            "\"last_Name\":\"Lastname2\"," +
            "\"userID\":\"user2\"," +
            "\"parkingSpots\":[],\"reservations\":[]}";

    String getAllUsersExpected = "{\"password\":\"password1\"," +
            "\"email\":\"Firstname1.Lastname1@gmail.com\"," +
            "\"isRenter\":true," +
            "\"isSeller\":false," +
            "\"first_name\":\"Firstname1\"," +
            "\"last_Name\":\"Lastname1\"," +
            "\"userID\":\"id1\"," +
            "\"parkingSpots\":[],\"reservations\":[]}," +
            "{\"password\":\"password2\"," +
            "\"email\":\"Firstname2.Lastname2@gmail.com\"," +
            "\"isRenter\":false," +
            "\"isSeller\":true," +
            "\"first_name\":\"Firstname2\"," +
            "\"last_Name\":\"Lastname2\"," +
            "\"userID\":\"user2\"," +
            "\"parkingSpots\":[],\"reservations\":[]}";

    @Before
    public void setup() throws Exception {
        String pm = "{\"pkey\":1}";

        mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pm))
                .andDo(print());
    }

    @After
    public void tearDown() throws Exception {
        mockMvc.perform(delete("/manager/pkey/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void addRenterTest() throws Exception {

        ResultActions result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1))
                .andDo(print())
                .andExpect(status().isOk());

        String resultString = "User Created";


        MvcResult resultBody = result.andReturn();
        assertTrue(resultBody.getResponse().getContentAsString().equals(resultString));
    }
    
    @Test
    public void addSellerTest() throws Exception {

        ResultActions result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user2))
                .andDo(print())
                .andExpect(status().isOk());

        String resultString = "User Created";

        MvcResult resultBody = result.andReturn();
        assertTrue(resultBody.getResponse().getContentAsString().equals(resultString));
    }

    @Test
    public void getAllUsersTest() throws Exception {

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
        assertThat(resultBody.getResponse().getContentAsString().equals(getAllUsersExpected));
    }

    @Test
    public void getUserByFirstNameTest() throws Exception {

        // Post to the local database
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1))
                .andDo(print())
                .andExpect(status().isOk());

        // Get user
        ResultActions getByFirstNameResult = mockMvc.perform(get("/user/firstname/Firsname1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // Check if response match
        MvcResult resultBody = getByFirstNameResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(user1Expected));
    }

    @Test
    public void getUserByLastNameTest() throws Exception {

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
        assertThat(resultBody.getResponse().getContentAsString().equals(user1Expected));
    }

    @Test
    public void getUserByIDTest() throws Exception {

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
        assertThat(resultBody.getResponse().getContentAsString().equals(user1Expected));
    }
    
    @Test
    public void deleteUserById() throws Exception 
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
    public void authenticateWithEmail() throws Exception {

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
    public void authenticateWithUsername() throws Exception {

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
    public void authenticationFailsOnWrongCredentials() throws Exception {

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
