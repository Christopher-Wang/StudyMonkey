package com.studymonkey.surveychimp.entity.wrapper;

import com.studymonkey.surveychimp.entity.questions.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class McQuestionWrapper extends QuestionWrapper{
    private List<String> options;

    public McQuestionWrapper(long surveyId, String question, QuestionType questionType){
        super(surveyId, question, questionType);
        this.options = new ArrayList<String>();
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
