package com.studymonkey.surveychimp.entity.questions;

public enum QuestionType {
    MULTIPLE_CHOICE(1),
    TEXT(2),
    RANGE(3);

    private final int value;

    private QuestionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QuestionType getQuestionTypeFromValue(int value) {
        switch (value) {
            case 1:
                return QuestionType.MULTIPLE_CHOICE;
            case 2:
                return QuestionType.TEXT;
            case 3:
                return QuestionType.RANGE;
        }

        return null;
    }
}
