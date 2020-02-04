package com.exercise.demomicroservice.services;

import com.exercise.demomicroservice.models.Account;
import com.exercise.demomicroservice.models.Operation;

import java.util.List;

public interface OperationService {
    List<Operation> getAllOperationsPerAccount(String accountId);
    List<Account> getAllAccountsPerUser(String userId);
    Operation saveOperation(Operation operation);
}
