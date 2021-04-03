package com.studymonkey.surveychimp.repositories;

import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.survey.SurveyStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "questionApi", path = "questionApi")
public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findAll();
    Question findById(long id);


    @Transactional
    @Modifying
    @Query("update RangeQuestion rq set rq.min = :min, rq.max =:max where rq.id =:id")
    void updateRangedQuestion(@Param(value = "min") int min,
                      @Param(value = "max") int max,
                      @Param(value = "id") Long id);
}
