package com.studymonkey.surveychimp.entity.answers;

import com.studymonkey.surveychimp.entity.Account;
import com.studymonkey.surveychimp.entity.questions.McOption;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class McAnswer extends Answer {

    @ManyToOne
    private McOption mcOption;

    public McAnswer(){}

    public McAnswer(AnswerType answerType, McOption mcOption) {
        super(answerType);
        this.mcOption = mcOption;
    }

    public McOption getMcOption() {
        return mcOption;
    }

    public void setMcOption(McOption mcOption) {
        this.mcOption = mcOption;
    }
}
