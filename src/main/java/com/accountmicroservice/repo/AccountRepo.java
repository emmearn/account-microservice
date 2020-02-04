package com.accountmicroservice.repo;

import com.accountmicroservice.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account, String> {

    @Query(value = "SELECT * FROM accounts WHERE fk_user=:id", nativeQuery = true)
    List<Account> getAllAccountsPerUser(@Param("id") String id);

    List<Account> findByFkUser(String fkUser);
}
