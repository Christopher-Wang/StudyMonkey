package com.studymonkey.surveychimp.repositories;

import com.studymonkey.surveychimp.entity.metrics.Metric;
import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.entity.survey.SurveyStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "surveyApi", path = "surveyApi")
public interface SurveyRepository extends CrudRepository<Survey, Long> {
    Survey findById(long id);
    List<Survey> findAll();

    @Transactional
    @Modifying
    @Query("update Survey s set s.name = :name, s.description =:description, s.status =:surveyStatus where s.id =:id")
    void updateSurvey(@Param(value = "name") String name,
                      @Param(value = "description") String description,
                      @Param(value = "surveyStatus") SurveyStatus surveyStatus,
                      @Param(value = "id") Long id);

}