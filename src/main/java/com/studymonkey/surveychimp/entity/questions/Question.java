package com.studymonkey.surveychimp.entity.questions;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.survey.Survey;

import javax.persistence.*;
import java.util.List;
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "question")
    private List<Answer> answers;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "surveyId")
    private Survey survey;

    private String question;

    private QuestionType questionType;

    public Question(){}

    public Question(String question, QuestionType questionType){
        this.question = question;
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
}
