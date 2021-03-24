package com.studymonkey.surveychimp.entity.answers;

import com.studymonkey.surveychimp.entity.Account;

import javax.persistence.Entity;

@Entity
public class TextAnswer extends Answer {
    private String questionAnswer;

    public TextAnswer(){}

    public TextAnswer(AnswerType answerType, String questionAnswer) {
        super(answerType);
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
}
