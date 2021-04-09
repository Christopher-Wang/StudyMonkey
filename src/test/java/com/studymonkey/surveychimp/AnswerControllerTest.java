package com.studymonkey.surveychimp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studymonkey.surveychimp.entity.Account;
import com.studymonkey.surveychimp.entity.answers.AnswerType;
import com.studymonkey.surveychimp.entity.answers.McAnswer;
import com.studymonkey.surveychimp.entity.answers.TextAnswer;
import com.studymonkey.surveychimp.entity.questions.QuestionType;
import com.studymonkey.surveychimp.entity.wrapper.McQuestionWrapper;
import com.studymonkey.surveychimp.entity.wrapper.RangeQuestionWrapper;
import com.studymonkey.surveychimp.entity.wrapper.TextQuestionWrapper;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.Cookie;
import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnswerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeAll
    public void setup() throws Exception {
        // Create the Account first
        // Account will have id = 1
        Account account = new Account("test", "test.email.com", "test");
        account = accountRepository.save(account);
        Cookie cookie = new Cookie("accountId", Long.toString(account.getId()));

        // Create the Survey next
        // Survey will have id = 2
        mockMvc.perform(post("/surveyV2/surveycreation")
                .cookie(cookie)
                .param("name","Test Survey #3")
                .param("description","This is a test")
                .param("status", "1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());

        // Text question will have id = 3
        TextQuestionWrapper wrapper = new TextQuestionWrapper(2, "What is your name?", QuestionType.TEXT);
        String questionJSON = asJsonString(wrapper);
        mockMvc.perform(post("/question")
                .param("questionType", "TEXT")
                .content(questionJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Mc question will have id = 4
        ArrayList<String> options = new ArrayList<String>();
        options.add("Option 1"); // McOption will have id = 5
        options.add("Option 2"); // McOption will have id = 6
        McQuestionWrapper wrapperMc = new McQuestionWrapper(2, "Test MC Question", QuestionType.MULTIPLE_CHOICE);
        wrapperMc.setOptions(options);
        String questionMcJSON = asJsonString(wrapperMc);
        mockMvc.perform(post("/question")
                .param("questionType", "MULTIPLE_CHOICE")
                .content(questionMcJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Range question will have id = 7
        RangeQuestionWrapper wrapperRange = new RangeQuestionWrapper(0,10,2L, "Test Range Question", QuestionType.RANGE);
        String questionRangeJSON = asJsonString(wrapperRange);
        mockMvc.perform(post("/question")
                .param("questionType", "RANGE")
                .content(questionRangeJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addTextAnswer() throws Exception {

        mockMvc.perform(post("/answer/textAnswer/3")
                .param("questionAnswer","This is my answer to the question!")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }

    @Test
    public void addMcAnswer() throws Exception {

        mockMvc.perform(post("/answer/mcAnswer/4")
                .param("mcOptionId","5")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }

    @Test
    public void addRangeAnswer() throws Exception {
        mockMvc.perform(post("/answer/rangeAnswer/7")
                .param("rangeAnswer","7")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }

    @Test
    public void getQuestionAnswers() throws Exception {
        // Get answer for text question (id=3)
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/answer/questionAnswers/3"))
                .andDo(print())
                .andExpect(status().isOk());

        // Get answer for mc question (id=4)
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/answer/questionAnswers/4"))
                .andDo(print())
                .andExpect(status().isOk());

        // Get answer for range question (id=7)
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/answer/questionAnswers/7"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAllRangeAnswersForASpecificQuestion() throws Exception {
        // Answer a range question
        mockMvc.perform(post("/answer/rangeAnswer/7")
                .param("rangeAnswer","7")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
        mockMvc.perform(post("/answer/rangeAnswer/7")
                .param("rangeAnswer","8")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
        // Get answer for range question (id=6)
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/answer/questionAnswersJSON/7"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("7")))
                .andExpect(content().string(containsString("8")));
    }

    @Test
    public void getSpecificAnswer() throws Exception {
        // Get answer for text question (id=3)
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/answer/3"))
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
