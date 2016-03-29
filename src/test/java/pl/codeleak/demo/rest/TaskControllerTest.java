package pl.codeleak.demo.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.codeleak.demo.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TaskControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void validRequestReturns200OK() throws Exception {
        testValidRequestReturns200OK("/rest/task");
        testValidRequestReturns200OK("/rest/task2");
        testValidRequestReturns200OK("/rest/task3");
    }

    private void testValidRequestReturns200OK(String url) throws Exception {
        String jsonTask = String.format("{\"name\": \"Task 1\",\"description\": \"Description\"}");

        this.mockMvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonTask))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(jsonTask)); // Spring 4.1. Requires org.skyscreamer.jsonassert.JSONAssert

    }

    @Test
    public void invalidNameError() throws Exception {
        testInvalidNameError("/rest/task");
        testInvalidNameError("/rest/task2");
        testInvalidNameError("/rest/task3");
    }

    private void testInvalidNameError(String url) throws Exception {
        String jsonTaskWithBlankName = String.format("{\"name\": \"\",\"description\": \"Description\"}");

        this.mockMvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonTaskWithBlankName))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(content().json("{\"errors\":[\"Task name must not be blank!\"],\"errorMessage\":\"Validation failed. 1 error(s)\"}"));

    }
}