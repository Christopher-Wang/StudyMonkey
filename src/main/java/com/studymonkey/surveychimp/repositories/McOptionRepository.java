package com.studymonkey.surveychimp.repositories;

import com.studymonkey.surveychimp.entity.questions.McOption;
import com.studymonkey.surveychimp.entity.questions.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "mcOptionApi", path = "mcOptionApi")
public interface McOptionRepository extends CrudRepository<McOption, Long> {
    @Query("select q from McOption q where q.question.id = :questionId")
    List<McOption> findAllByQuestionId(long questionId);
}
