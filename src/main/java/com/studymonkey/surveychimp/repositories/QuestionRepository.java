package com.studymonkey.surveychimp.repositories;

import com.studymonkey.surveychimp.entity.questions.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "questionApi", path = "questionApi")
public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findAll();
    Question findById(long id);
    @Query("select q from Question q where q.survey.id = :id and q.question = :question")
    List<Question> findBySurveyAndQuestion(long id, String question);
}
