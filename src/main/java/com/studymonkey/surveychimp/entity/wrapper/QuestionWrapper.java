package com.studymonkey.surveychimp.entity.wrapper;

import com.studymonkey.surveychimp.entity.questions.Question;

public class QuestionWrapper {

    private long surveyId;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    private Question question;

    public QuestionWrapper(){
    }

    public QuestionWrapper(long surveyId, Question question){
        this.surveyId = surveyId;
        this.question = question;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }
}
