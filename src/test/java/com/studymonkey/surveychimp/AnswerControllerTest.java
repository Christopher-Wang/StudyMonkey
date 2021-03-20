package com.studymonkey.surveychimp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studymonkey.surveychimp.entity.answers.AnswerType;
import com.studymonkey.surveychimp.entity.answers.McAnswer;
import com.studymonkey.surveychimp.entity.answers.TextAnswer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnswerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public void setup() throws Exception {
        // Create the Survey first
        mockMvc.perform(post("/surveyV2/surveycreation")
                .param("name","Test Survey #2")
                .param("description","This is a test")
                .param("status", "1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());

        // Then create the question
        //Object o = new Object();
//        this.mockMvc.perform(MockMvcRequestBuilders
//                .post("/question/question/{id}", 1)
//                .content(asJsonString("Hello"))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());

        mockMvc.perform(post("/question")
                .param("surveyId","1")
                .param("question.question","?")
                .param("question.questionType","TEXT")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }

    @Test
    public void addTextAnswer() throws Exception {

        TextAnswer ans = new TextAnswer(AnswerType.TEXT, "This is my answer");
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/answer/textAnswer/{questionId}", 2)
                .content(asJsonString(ans))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addMcAnswer() throws Exception {

        McAnswer ans = new McAnswer(AnswerType.MULTIPLE_CHOICE, 1);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/answer/McAnswer/{questionId}", 2)
                .content(asJsonString(ans))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
