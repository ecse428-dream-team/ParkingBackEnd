package ca.mcgill.ecse428.parkingsystem.ControllerTests;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class AdminControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHello() throws Exception {
        ResultActions result = mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult resultBody = result.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals("Hello"));
    }
    
    @Test
    public void addAdminTest() throws Exception {
    	
    	String pm = "{\"pkey\":1}";
    	
    	mockMvc.perform(post("/manager")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(pm))
    			.andDo(print());
    	
    	String admin = "{\"firstName\":\"Owais\"," +
                "\"lastName\":\"Khan\"," +
                "\"id\":\"260617913\"," +
                "\"password\":\"something\"," +
                "\"email\":\"123@gmail.com\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"1\"}}";
    	
    	ResultActions result = mockMvc.perform(post("/admin")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(admin))
    			.andDo(print())
    			.andExpect(status().isOk());
    	
    	String resultString = "{\"password\":\"something\"," +
                "\"email\":\"123@gmail.com\"," +
                "\"first_name\":\"Owais\"," +
                "\"last_Name\":\"Khan\"," +
                "\"userID\":\"260617913\"}";
    	
    	MvcResult resultBody = result.andReturn();
    	assertTrue(resultBody.getResponse().getContentAsString().equals(resultString));
    }
    
    @Test
    public void getAllAdminsTest() throws Exception {
    	
    	String pm = "{\"pkey\":1}";

        mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pm))
                .andDo(print());
        
        String admin_1 = "{\"firstName\":\"Owais\"," +
                "\"lastName\":\"Khan\"," +
                "\"id\":\"260617913\"," +
                "\"password\":\"something\"," +
                "\"email\":\"123@gmail.com\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"1\"}}";
        
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(admin_1))
                .andDo(print())
                .andExpect(status().isOk());
        
        String admin_2 = "{\"firstName\":\"HyunSu\"," +
                "\"lastName\":\"An\"," +
                "\"id\":\"260775639\"," +
                "\"password\":\"hi\"," +
                "\"email\":\"1234@gmail.com\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"1\"}}";
        
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(admin_2))
                .andDo(print())
                .andExpect(status().isOk());
        
        String result_Admins = "{\"password\":\"something\"," +
                "\"email\":\"123@gmail.com\"," +
                "\"first_name\":\"Owais\"," +
                "\"last_Name\":\"Khan\"," +
                "\"userID\":\"260617913\"," +
                "\"password\":\"hi\"," +
                "\"email\":\"1234@gmail.com\"," +
                "\"first_name\":\"HyunSu\"," +
                "\"last_Name\":\"An\"," +
                "\"userID\":\"260775639\"}";
        
        ResultActions getAllResult = mockMvc.perform(get("/admin/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        
        MvcResult resultBody = getAllResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(result_Admins));
    }
    
    @Test
    public void getAdminByIDTest() throws Exception {
    	
    	String pm = "{\"pkey\":1}";

        mockMvc.perform(post("/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pm))
                .andDo(print());
        
        String admin = "{\"firstName\":\"HyunSu\"," +
                "\"lastName\":\"An\"," +
                "\"id\":\"260775639\"," +
                "\"password\":\"hi\"," +
                "\"email\":\"1234@gmail.com\"," +
                "\"parkingManager\":" +
                " {\"pkey\":\"1\"}}";
        
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(admin))
                .andDo(print())
                .andExpect(status().isOk());
        
        String resultString = "{\"password\":\"hi\"," +
                "\"email\":\"1234@gmail.com\"," +
                "\"first_name\":\"HyunSu\"," +
                "\"last_Name\":\"An\"," +
                "\"userID\":\"260775639\"}";
        
        ResultActions getByIDResult = mockMvc.perform(get("/admin/id/260775639")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        
        MvcResult resultBody = getByIDResult.andReturn();
        assertThat(resultBody.getResponse().getContentAsString().equals(resultString));
    }
    
}
