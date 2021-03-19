package com.studymonkey.surveychimp.repositories;

import com.studymonkey.surveychimp.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*
 * collectionResourceRel: show all accounts named accounts json
 * itemResourceRel: show an account named account json
 * path: the path - e.g. http://localhost:8080/accountApi
 */
@RepositoryRestResource(collectionResourceRel = "accounts", itemResourceRel = "account", path = "accountApi")
public interface AccountRepository extends CrudRepository<Account, Long> {

    // Spring JPA built in
    // GET request example: http://localhost:8080/accountApi/2
    Account findById(long id);

    @Query(
            value = "SELECT * FROM account WHERE account.username = :name",
            nativeQuery=true
    )
    Account findByUsername(@Param("name") String name);

    Account findByUsernameAndEmail(String username, String email);

    @Query(
            value = "SELECT * FROM account WHERE account.username = :name and account.password = :password",
            nativeQuery=true
    )
    Account findByUsernameAndPassword(@Param("name") String name, @Param("password") String password);
}
