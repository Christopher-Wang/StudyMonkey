package com.studymonkey.surveychimp;

import com.studymonkey.surveychimp.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SignupViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnSignupWebpage() throws Exception {
        this.mockMvc.perform(get("/signup")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Sign up")));
    }

    @Test
    public void shouldReturnAccountJSONAfterCreatedAccount() throws Exception
    {
        Account account = new Account("test1", "test1@gmail.com", "test1");
        this.mockMvc.perform(post("/signup/createAccount")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "test1")
                .param("email", "test1@gmail.com")
                .param("password", "test1").accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Create Survey")));
    }
}
