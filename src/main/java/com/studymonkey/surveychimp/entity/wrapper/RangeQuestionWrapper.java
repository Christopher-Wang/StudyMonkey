package com.studymonkey.surveychimp.entity.wrapper;

import com.studymonkey.surveychimp.entity.questions.Question;

public class RangeQuestionWrapper {

    private long surveyId;
    private long questionId;
    private int min;
    private int max;

    public RangeQuestionWrapper(long surveyId, long questionId, int min, int max){
        this.surveyId = surveyId;
        this.questionId = questionId;
        this.min = min;
        this.max = max;
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

    public RangeQuestionWrapper(){
    }

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }
}
