package com.studymonkey.surveychimp.entity.answers;

public class Answer {
    private int questionOrder;
    private int surveyId;
    private int userId;

    public int getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(int questionId) {
        this.questionOrder = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }
}
