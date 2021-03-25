package com.studymonkey.surveychimp.entity.metrics;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.studymonkey.surveychimp.entity.questions.McOption;
import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.survey.Survey;

import javax.persistence.*;

@Entity(name="Metric")
@Table(name="metric")
public class Metric {

    @EmbeddedId
    private MetricId id;

    @Column(name="percentagedAnswered",nullable = false)
    private int percentageAnswered;

    @JsonBackReference
    @OneToOne
    @MapsId("question_id")
    @JoinColumn(
            name="question_id",
            foreignKey = @ForeignKey(name="metric_question_id_fk")
    )
    private Question question;

    @JsonBackReference
    @OneToOne
    @MapsId("mcOption_id")
    @JoinColumn(
            name="mcOption_id",
            foreignKey = @ForeignKey(name="metric_mcOption_id_fk")
    )
    private McOption mcOption;

    @JsonBackReference
    @OneToOne
    @MapsId("survey_id")
    @JoinColumn(
            name="survey_id",
            foreignKey = @ForeignKey(name="metric_survey_id_fk")
    )
    private Survey survey;

    public Metric() {}

    public Metric(int percentageAnswered) {
        this.percentageAnswered = percentageAnswered;
    }

    public MetricId getId() {
        return id;
    }

    public void setId(MetricId id) {
        this.id = id;
    }

    public int getPercentageAnswered() {
        return percentageAnswered;
    }

    public void setPercentageAnswered(int percentageAnswered) {
        this.percentageAnswered = percentageAnswered;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public McOption getMcOption() {
        return mcOption;
    }

    public void setMcOption(McOption mcOption) {
        this.mcOption = mcOption;
    }
}
