package com.studymonkey.surveychimp.entity.wrapper;

import com.studymonkey.surveychimp.entity.questions.QuestionType;

public class TextQuestionWrapper extends QuestionWrapper{
    public TextQuestionWrapper(long surveyId, String question, QuestionType questionType) {
        super(surveyId, question, questionType);
    }
}
