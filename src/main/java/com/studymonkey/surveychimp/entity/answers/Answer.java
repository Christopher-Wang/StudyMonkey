package com.studymonkey.surveychimp.entity.answers;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="Answer")
@Table(name="question_answer")
public class Answer implements Serializable {

    @SequenceGenerator(
            name="question_answer_sequence",
            sequenceName= "question_answer_sequence",
            allocationSize= 1
    )
    @GeneratedValue (
            strategy= GenerationType.SEQUENCE,
            generator= "question_answer_sequence"
    )
    private long id;

    @Id
    @Column (
            name="question_order_id",
            columnDefinition="integer"
    )
    private long questionOrder;

    @Id
    @Column (
            name="survey_id",
            columnDefinition="integer"
    )
    private long surveyId;

    @Id
    @Column(
            name="client_id",
            columnDefinition="integer"
    )
    private long userId;

    public long getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(long questionId) {
        this.questionOrder = questionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }
}
