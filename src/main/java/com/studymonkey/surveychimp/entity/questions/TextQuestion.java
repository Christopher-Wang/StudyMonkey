package com.studymonkey.surveychimp.entity.questions;

import javax.persistence.Entity;

@Entity
public class TextQuestion extends Question {
    public TextQuestion(){}
    public TextQuestion(String question, QuestionType questionType){
        super(question, questionType);
    }
}
