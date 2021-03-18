package com.studymonkey.surveychimp.entity.survey;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A survey's details
 *
 * Note: the ID is auto incremented by the database, therefore it is not used
 * when inserting the record into the database.
 */
@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private SurveyStatus status;

    public Survey (SurveyStatus status){
        this.status = status;
    }

    public Survey() {
    }

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
        if(status == null){
            return SurveyStatus.CLOSED.getValue();
        }
        return status.getValue();
    }

    public void setStatus(int status) {
        this.setStatus(SurveyStatus.values()[status]);
    }

    public void setStatus(SurveyStatus status) {
        this.status = status;
    }
}
