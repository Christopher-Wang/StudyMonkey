package com.studymonkey.surveychimp.entity.answers;

import com.studymonkey.surveychimp.entity.answers.Answer;

public class McAnswer extends Answer {

    private int mcOptionIdAnswer;

    public int getMcOptionIdAnswer() {
        return mcOptionIdAnswer;
    }

    public void setMcOptionIdAnswer(int mcOptionIdAnswer) {
        this.mcOptionIdAnswer = mcOptionIdAnswer;
    }
}
