package com.studymonkey.surveychimp.entity.wrapper;

import com.studymonkey.surveychimp.entity.questions.McQuestion;
import com.studymonkey.surveychimp.entity.questions.Question;

public class McQuestionWrapper {

    private long surveyId;
    private McQuestion question;

    public McQuestionWrapper(){
    }

    public McQuestionWrapper(long surveyId, McQuestion question){
        this.surveyId = surveyId;
        this.question = question;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }

    public McQuestion getQuestion() {
        return question;
    }

    public void setQuestion(McQuestion question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "QuestionWrapper{" +
                "surveyId=" + surveyId +
                ", question=" + question +
                '}';
    }
}
