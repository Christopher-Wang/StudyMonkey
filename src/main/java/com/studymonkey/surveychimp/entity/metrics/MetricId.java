package com.studymonkey.surveychimp.entity.metrics;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MetricId implements Serializable {

    @Column(name="question_id")
    private long questionId;
    @Column(name="mcOption_id")
    private long mcOptionId;
    @Column(name="survey_id")
    private long surveyId;

    public MetricId(long questionId, long mcOptionId, long surveyId) {
        this.questionId = questionId;
        this.mcOptionId = mcOptionId;
        this.surveyId = surveyId;
    }

    public MetricId(){}

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getMcOptionId() {
        return mcOptionId;
    }

    public void setMcOptionId(long mcOptionId) {
        this.mcOptionId = mcOptionId;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }
}
