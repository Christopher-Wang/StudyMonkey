package com.studymonkey.surveychimp.entity.survey;

/**
 * A survey's details
 *
 * Note: the ID is auto incremented by the database, therefore it is not used
 * when inserting the record into the database.
 */
public class Survey {

    private int surveyId;
    private String name;
    private String description;
    private SurveyStatus status;

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SurveyStatus getStatus() {
        return status;
    }

    public void setStatus(SurveyStatus status) {
        this.status = status;
    }
}
