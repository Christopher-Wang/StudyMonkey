package com.studymonkey.surveychimp.entity.survey;

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
