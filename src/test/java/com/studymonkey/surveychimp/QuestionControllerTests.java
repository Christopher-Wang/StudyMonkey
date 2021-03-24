package com.studymonkey.surveychimp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.questions.QuestionType;
import com.studymonkey.surveychimp.entity.wrapper.QuestionWrapper;
import com.studymonkey.surveychimp.viewControllers.SurveyViewController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class QuestionControllerTests {
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
    public void getQuestionFormTest() throws Exception{
        mockMvc.perform(get("/question")
                .param("surveyId", "0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getQuestionListTest() throws Exception{
        mockMvc.perform(get("/question/questionList")
                .param("surveyId", "0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void createQuestionTest() throws Exception{
        mockMvc.perform(post("/question")
                .param("surveyId","1")
                .param("question.question","?")
                .param("question.questionType","TEXT")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }
}
