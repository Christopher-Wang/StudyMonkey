package com.studymonkey.surveychimp.entity.survey;

public enum SurveyStatus {
    OPEN(0),
    CLOSED(1);

    private final int value;
    private SurveyStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
