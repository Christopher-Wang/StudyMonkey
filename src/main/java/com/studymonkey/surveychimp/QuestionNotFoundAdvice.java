package com.studymonkey.surveychimp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class QuestionNotFoundAdvice {

    @ResponseBody // render into the response body
    @ExceptionHandler(QuestionNotFoundException.class) // only response when QuestionNotFoundException is thrown
    @ResponseStatus(HttpStatus.NOT_FOUND) // issue 404
    String employeeNotFoundHandler(QuestionNotFoundException ex) {
        return ex.getMessage();
    }
}