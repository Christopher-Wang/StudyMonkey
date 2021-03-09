package com.studymonkey.surveychimp.entity.survey;

/**
 * A survey's details
 *
 * Note: the ID is auto incremented by the database, therefore it is not used
 * when inserting the record into the database.
 */
public class Survey {

    private int id;
    private String name;
    private String description;
    private SurveyStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStatus() {
        //return status;
        return status.getValue();
    }

    public void setStatus(SurveyStatus status) {
        this.status = status;
    }
}
