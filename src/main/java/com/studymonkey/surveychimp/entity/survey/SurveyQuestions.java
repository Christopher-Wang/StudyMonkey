package com.studymonkey.surveychimp.entity.survey;

import com.studymonkey.surveychimp.entity.questions.Question;

import java.util.List;

public class SurveyQuestions extends Survey {

    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
