package com.studymonkey.surveychimp.repositories;

import com.studymonkey.surveychimp.entity.survey.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "surveyApi", path = "surveyApi")
public interface SurveyRepository extends CrudRepository<Survey, Long> {
    Survey findById(long it);
    List<Survey> findAll();
}