package com.studymonkey.surveychimp;

import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.questions.McQuestion;
import com.studymonkey.surveychimp.entity.survey.Survey;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EntityTests {
    private Survey survey;

    @Before
    public void setUp() throws Exception {
         this.survey = new Survey();
    }

    @Test
    public void testAnswer() {
//        Answer answer = new Answer();
//        answer.setSurveyId(1);
//
//        assertEquals("Check that answer surveyId was set properly", answer.getSurveyId(), 1);
    }

    @Test
    public void testMcQuestion() {
//        McQuestion mc = new McQuestion();
//        ArrayList<String> list = new ArrayList<>();
//        list.add("option 1");
//        list.add("option 2");
//        mc.setMcOption(list);
//
//        assertEquals("Check that mcOption was set properly", mc.getMcOption().size(),2);
    }

    @Test
    public void testSurvey() {
        survey.setId(3);
        assertEquals("Check that survey was set properly", survey.getId(), 3);
    }
}
