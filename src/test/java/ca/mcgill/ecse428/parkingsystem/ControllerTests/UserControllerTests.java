package ca.mcgill.ecse428.parkingsystem.ControllerTests;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    public void addUserTest() throws Exception {

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

        String resultString = "{\"password\":\"something\"," +
                "\"email\":\"123@gmail.com\"," +
                "\"isRenter\":true," +
                "\"isSeller\":false," +
                "\"first_name\":\"Owais\"," +
                "\"last_Name\":\"Khan\"," +
                "\"userID\":\"260617913\"," +
                "\"parkingSpots\":[],\"reservations\":[]}";

        MvcResult resultBody = result.andReturn();
        assertTrue(resultBody.getResponse().getContentAsString().equals(resultString));
    }



}
