package com.studymonkey.surveychimp;


import com.studymonkey.surveychimp.entity.Account;
import com.studymonkey.surveychimp.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class LoginViewControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Account account;
    @Autowired
    private AccountRepository repository;

    @BeforeAll
    public void setup() throws Exception {
        account = new Account("username", "test1@gmail.com", "password");
        repository.save(account);
    }

    @Test
    public void shouldReturnSignupWebpage() throws Exception {
        this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Account Login")));
    }

    @Test
    public void shouldReturnBeOK() throws Exception {
        this.mockMvc.perform(get("/accounts")
                .param("username", account.getUsername())
                .param("password", "password")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBeNotFound() throws Exception {
        this.mockMvc.perform(get("/accounts")
                .param("username", account.getUsername())
                .param("password", "WrongPassword")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isNoContent());
    }

}
