package com.studymonkey.surveychimp;

public class QuestionNotFoundException extends RuntimeException {

    QuestionNotFoundException(Long id) {
        super("Could not find question " + id);
    }
}
