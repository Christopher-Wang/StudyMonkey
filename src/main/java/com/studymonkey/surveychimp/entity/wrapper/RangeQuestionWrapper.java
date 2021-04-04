package com.studymonkey.surveychimp.entity.wrapper;

import com.studymonkey.surveychimp.entity.questions.QuestionType;

public class RangeQuestionWrapper extends QuestionWrapper {
    private int min;
    private int max;

    public RangeQuestionWrapper(){
        super();
    }

    public RangeQuestionWrapper(int min, int max, Long surveyId, String question, QuestionType questionType){
        super(surveyId,question,questionType);
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
