package com.studymonkey.surveychimp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studymonkey.surveychimp.viewControllers.SurveyViewController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SurveyTests {
    @LocalServerPort
    private int port;

    @Autowired
    private SurveyViewController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getSurveyPageTest() throws Exception{
        mockMvc.perform(get("/surveyV2")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getSurveyListTest() throws Exception{
        mockMvc.perform(get("/surveyV2/surveyList")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void createSurveyTest() throws Exception{
        mockMvc.perform(post("/surveyV2/surveycreation")
                .param("name","Test Survey #2")
                .param("description","This is a test")
                .param("status", "1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }

}
