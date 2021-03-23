package com.studymonkey.surveychimp.entity.survey;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.studymonkey.surveychimp.entity.questions.Question;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private long id;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "survey")
    private List<Question> questions;
    private String name;
    private String description;
    private SurveyStatus status;

    public Survey() {
        this.questions = new ArrayList<Question>();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
            // Default case if the survey doesnt have a status set
            return SurveyStatus.CLOSED.getValue();
        }
        return status.getValue();
    }

    /**
     * Helper method to set the status from an int value. Used to map the int value returned
     * by a radio button in the UI.
     * @param status the status of the survey
     */
    public void setStatus(int status) {
        this.setStatus(SurveyStatus.values()[status]);
    }

    public void setStatus(SurveyStatus status) {
        this.status = status;
    }
}
