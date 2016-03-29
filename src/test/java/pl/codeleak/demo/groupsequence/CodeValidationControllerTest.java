package pl.codeleak.demo.groupsequence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.codeleak.demo.Application;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CodeValidationControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void displaysCodeForm() throws Exception {
        this.mockMvc.perform(get("/groupsequence/code"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("code", any(Code.class)))
                    .andExpect(view().name("groupsequence/code"));
    }


    @Test
    public void postsValidCode() throws Exception {
        this.mockMvc.perform(post("/groupsequence/code")
            .param("code", "1"))
                    .andExpect(model().hasNoErrors())
                    .andExpect(content().string(not(containsString("Form contains errors."))));
    }

    @Test
    public void postsInvalidValidCode() throws Exception {
        this.mockMvc.perform(post("/groupsequence/code")
            .param("code", "42"))
                    .andExpect(model().hasErrors())
                    .andExpect(model().attributeHasErrors("code"))
                    .andExpect(model().attributeHasFieldErrorCode("code", "code", "ExistingCode")) // Spring 4.1
                    .andExpect(content().string(containsString("Form contains errors.")))
                    .andExpect(content().string(containsString("Code is invalid!")));
    }

    @Test
    public void validationFailsOnEmptyCode() throws Exception {
        this.mockMvc.perform(post("/groupsequence/code")
            .param("code", ""))
                    .andExpect(model().hasErrors())
                    .andExpect(model().attributeHasErrors("code"))
                    .andExpect(content().string(containsString("Form contains errors. Please try again.")))
                    .andExpect(content().string(containsString("size must be between 1 and 3")));
    }
}