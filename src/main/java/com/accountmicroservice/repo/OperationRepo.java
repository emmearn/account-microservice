package com.accountmicroservice.repo;

import com.accountmicroservice.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationRepo extends JpaRepository<Operation, String> {

    @Query(value = "SELECT * FROM operations WHERE receiver=:id OR sender=id", nativeQuery = true)
    List<Operation> findAllOperationsByAccount(@Param("id") String id);
}
