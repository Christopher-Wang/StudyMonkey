package com.studymonkey.surveychimp.repositories;

import com.studymonkey.surveychimp.entity.metrics.Metric;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MetricRepository extends CrudRepository<Metric, Long> {

    @Query("select m from Metric m where m.survey = ?1")
    public List<Metric> findAllBySurveyId(long id);
}
