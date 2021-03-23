package com.studymonkey.surveychimp.repositories;

import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.questions.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "answerApi", path = "answerApi")
public interface AnswerRepository extends CrudRepository<Answer, Long> {
    List<Answer> findAll();
    Answer findById(long id);
}
