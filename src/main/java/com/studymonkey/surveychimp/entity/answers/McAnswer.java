package com.studymonkey.surveychimp.entity.answers;

import com.studymonkey.surveychimp.entity.Account;

import javax.persistence.Entity;

@Entity
public class McAnswer extends Answer {

    private int mcOptionIdAnswer;

    public McAnswer(){}

    public McAnswer(AnswerType answerType, int mcOptionIdAnswer) {
        super(answerType);
        this.mcOptionIdAnswer = mcOptionIdAnswer;
    }

    public int getMcOptionIdAnswer() {
        return mcOptionIdAnswer;
    }

    public void setMcOptionIdAnswer(int mcOptionIdAnswer) {
        this.mcOptionIdAnswer = mcOptionIdAnswer;
    }
}
