package com.studymonkey.surveychimp.entity.wrapper;


import com.studymonkey.surveychimp.entity.questions.QuestionType;

public class QuestionWrapper {

    private long surveyId;
    private String question;
    private QuestionType questionType;

    public QuestionWrapper(){
    }

    public QuestionWrapper(long surveyId, String question, QuestionType questionType){
        this.surveyId = surveyId;
        this.question = question;
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
}
