package com.studymonkey.surveychimp.repositories;

import com.studymonkey.surveychimp.entity.questions.McOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "questionApi", path = "questionApi")
public interface McOptionRepository extends CrudRepository<McOption, Long> {
    List<McOption> findAll();
    McOption findById(long id);
}
