package com.studymonkey.surveychimp.entity.answers;

import com.studymonkey.surveychimp.entity.questions.QuestionType;

public enum AnswerType {
    MULTIPLE_CHOICE(1),
    TEXT(2);

    private final int value;

    private AnswerType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AnswerType getAnswerTypeFromValue(int value) {
        switch (value) {
            case 1:
                return AnswerType.MULTIPLE_CHOICE;
            case 2:
                return AnswerType.TEXT;
        }

        return null;
    }
}
