package com.studymonkey.surveychimp.repositories;

import com.studymonkey.surveychimp.entity.questions.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "surveyApi", path = "surveyApi")
public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findAll();
}