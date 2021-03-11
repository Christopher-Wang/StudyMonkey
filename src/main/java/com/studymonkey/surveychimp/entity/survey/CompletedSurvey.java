package com.studymonkey.surveychimp.entity.survey;

import com.studymonkey.surveychimp.entity.answers.Answer;

import java.util.List;

public class CompletedSurvey extends SurveyQuestions {

    private List<Answer> answers;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
