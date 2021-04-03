package com.studymonkey.surveychimp.entity.wrapper;

public class RangeQuestionWrapper{

    public long surveyId;
    public long questionId;
    private int min;
    private int max;

    public RangeQuestionWrapper(int min, int max, Long surveyId, Long questionId){
        this.surveyId = surveyId;
        this.questionId = questionId;
        this.min = min;
        this.max = max;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
