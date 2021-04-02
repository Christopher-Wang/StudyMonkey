package com.studymonkey.surveychimp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studymonkey.surveychimp.entity.questions.*;
import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.entity.survey.SurveyStatus;
import com.studymonkey.surveychimp.entity.wrapper.McQuestionWrapper;
import com.studymonkey.surveychimp.entity.wrapper.QuestionWrapper;
import com.studymonkey.surveychimp.repositories.McOptionRepository;
import com.studymonkey.surveychimp.repositories.QuestionRepository;
import com.studymonkey.surveychimp.repositories.SurveyRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTests {

    @Autowired
    private MockMvc mockMvc;
    private Survey survey;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private McOptionRepository mcOptionRepository;

    @BeforeAll
    public void setup() throws Exception {
        survey = new Survey();
        survey.setStatus(SurveyStatus.OPEN);
        survey.setName("survey");
        survey.setDescription("Description");

        survey = surveyRepository.save(survey);
    }

    @Test
    public void shouldCreateMcQuestion() throws Exception {
        McQuestion question = new McQuestion();
        question.setQuestion("What is your favourite movie?");
        question.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        question.setSurvey(survey);

        List<McOption> options = new ArrayList<>();
        options.add(new McOption("movie_1"));
        options.add(new McOption("movie_2"));
        options.add(new McOption("movie_3"));

        question.setMcOption(options);

        McQuestionWrapper wrapper = new McQuestionWrapper();
        wrapper.setQuestion(question);
        wrapper.setSurveyId(survey.getId());

        List<Question> persitedQuestion = questionRepository.findBySurveyAndQuestion(survey.getId(), question.getQuestion());
        assertEquals(true, persitedQuestion.isEmpty());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/questionJson/mc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(wrapper)))
                .andExpect(status().isCreated());

        persitedQuestion = questionRepository.findBySurveyAndQuestion(survey.getId(), question.getQuestion());
        assertEquals(question.getQuestion(), persitedQuestion.get(0).getQuestion());
        assertEquals(question.getQuestionType(), persitedQuestion.get(0).getQuestionType());

        List<McOption> mcOptions = mcOptionRepository.findAllByQuestionId(persitedQuestion.get(0).getId());
        List<String> optionText = mcOptions.stream().map(option -> option.getOption()).
                collect(Collectors.toList());

        System.out.println(optionText);
        assertEquals(true,optionText.contains(options.get(0).getOption()));
        assertEquals(true,optionText.contains(options.get(1).getOption()));
        assertEquals(true,optionText.contains(options.get(2).getOption()));

        //Deconstruct
        questionRepository.delete(persitedQuestion.get(0));
    }
    @Test
    public void shouldReturnBadRequestMcQuestion() throws Exception {
        Survey survey = new Survey();
        survey.setId(99999);

        McQuestion question = new McQuestion();
        question.setQuestion("What is your favourite movie?");
        question.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        question.setSurvey(survey);

        List<McOption> options = new ArrayList<>();
        options.add(new McOption("movie_1"));
        options.add(new McOption("movie_2"));
        options.add(new McOption("movie_3"));

        question.setMcOption(options);

        McQuestionWrapper wrapper = new McQuestionWrapper();
        wrapper.setQuestion(question);
        wrapper.setSurveyId(survey.getId());

        Survey surveyFind = surveyRepository.findById(99999);
        assertEquals(null, surveyFind);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/questionJson/mc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(wrapper)))
                .andExpect(status().isBadRequest());

        question.setSurvey(this.survey);
        question.setQuestionType(QuestionType.TEXT);
        wrapper.setQuestion(question);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/questionJson/mc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(wrapper)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreteTextQuestion() throws Exception {
        TextQuestion question = new TextQuestion();
        question.setQuestion("What is your favourite movie?");
        question.setQuestionType(QuestionType.TEXT);
        question.setSurvey(survey);

        QuestionWrapper wrapper = new QuestionWrapper();
        wrapper.setQuestion(question);
        wrapper.setSurveyId(survey.getId());

        List<Question> persitedQuestion = questionRepository.findBySurveyAndQuestion(survey.getId(), question.getQuestion());
        assertEquals(true, persitedQuestion.isEmpty());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/questionJson/text")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(wrapper)))
                .andExpect(status().isCreated());

        persitedQuestion = questionRepository.findBySurveyAndQuestion(survey.getId(), question.getQuestion());
        assertEquals(question.getQuestion(), persitedQuestion.get(0).getQuestion());
        assertEquals(question.getQuestionType(), persitedQuestion.get(0).getQuestionType());

        //Deconstruct
        questionRepository.delete(persitedQuestion.get(0));
    }

    @Test
    public void shouldReturnBadRequestTextQuestion() throws Exception {
        Survey survey = new Survey();
        survey.setId(99999);

        McQuestion question = new McQuestion();
        question.setQuestion("What is your favourite movie?");
        question.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        question.setSurvey(survey);

        List<McOption> options = new ArrayList<>();
        options.add(new McOption("movie_1"));
        options.add(new McOption("movie_2"));
        options.add(new McOption("movie_3"));

        question.setMcOption(options);

        McQuestionWrapper wrapper = new McQuestionWrapper();
        wrapper.setQuestion(question);
        wrapper.setSurveyId(survey.getId());

        Survey surveyFind = surveyRepository.findById(99999);
        assertEquals(null, surveyFind);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/questionJson/mc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(wrapper)))
                .andExpect(status().isBadRequest());

        question.setSurvey(this.survey);
        question.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        wrapper.setQuestion(question);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/questionJson/mc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(wrapper)))
                .andExpect(status().isBadRequest());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
