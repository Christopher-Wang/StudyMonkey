package com.studymonkey.surveychimp.entity.answers;

import javax.persistence.Entity;

@Entity
public class RangeAnswer extends Answer {
    private int numAnswer;

    public RangeAnswer() {

    }

    public RangeAnswer (AnswerType answerType, int numAnswer) {
        super(answerType);
        this.numAnswer = numAnswer;
    }

    public int getNumAnswer() {
        return numAnswer;
    }

    public void setNumAnswer(int numAnswer) {
        this.numAnswer = numAnswer;
    }
}
