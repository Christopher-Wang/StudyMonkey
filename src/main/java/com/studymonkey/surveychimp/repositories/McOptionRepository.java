package com.studymonkey.surveychimp.repositories;

import com.studymonkey.surveychimp.entity.questions.McOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "mcOptionApi", path = "mcOptionApi")
public interface McOptionRepository extends CrudRepository<McOption, Long> {

}
