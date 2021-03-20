package com.studymonkey.surveychimp.entity.questions;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


@Entity
public class McOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "questionId")
    private McQuestion question;

    private String option;

    public McOption(){}

    public McOption(String option){
        this.option = option;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public McQuestion getQuestion() {
        return question;
    }

    public void setQuestion(McQuestion question) {
        this.question = question;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
