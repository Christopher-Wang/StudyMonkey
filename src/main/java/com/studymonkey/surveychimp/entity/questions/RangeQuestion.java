package com.studymonkey.surveychimp.entity.questions;

import javax.persistence.Entity;

@Entity
public class RangeQuestion extends Question {
    private int min;
    private int max;

    public RangeQuestion() {
    }

    public RangeQuestion(String question, int min, int max, QuestionType questionType) {
        super(question, questionType);
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
