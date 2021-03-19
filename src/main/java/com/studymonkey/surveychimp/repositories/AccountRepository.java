package com.studymonkey.surveychimp.repositories;

import com.studymonkey.surveychimp.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "userApi", path = "userApi")
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findById(long id);

    @Query(
            value = "SELECT * FROM account WHERE account.user_name = :name",
            nativeQuery=true
    )
    Account findByUsername(@Param("name") String name);

}
