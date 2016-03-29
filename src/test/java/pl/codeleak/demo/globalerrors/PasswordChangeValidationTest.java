package pl.codeleak.demo.globalerrors;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pl.codeleak.demo.globalerrors.GlobalErrorsMatchers.globalErrors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PasswordChangeValidationTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void failsWhenEmptyPasswordsGiven() throws Exception {
        this.mockMvc.perform(post("/globalerrors/password")
            .param("password", "").param("confirmedPassword", ""))
                    .andExpect(model().attributeHasFieldErrors("passwordForm", "password", "confirmedPassword"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("globalerrors/password"));
    }

    @Test
    public void failsWhenDifferentPasswordsGiven() throws Exception {
        this.mockMvc.perform(post("/globalerrors/password")
            .param("password", "test").param("confirmedPassword", "other"))
                    .andExpect(model().hasErrors())
                    .andExpect(status().isOk())
                    .andExpect(view().name("globalerrors/password"));
    }

    @Test
    public void failsWithGlobalErrorWhenDifferentPasswordsGiven() throws Exception {
        this.mockMvc.perform(post("/globalerrors/password")
            .param("password", "test").param("confirmedPassword", "other"))
                    .andExpect(globalErrors().hasGlobalError("passwordForm", "passwords do not match"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("globalerrors/password"));
    }

    @Test
    public void passesWhenSamePasswordsGiven() throws Exception {
        this.mockMvc.perform(post("/globalerrors/password")
            .param("password", "test").param("confirmedPassword", "test"))
                    .andExpect(model().hasNoErrors())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:password"));
    }

}
