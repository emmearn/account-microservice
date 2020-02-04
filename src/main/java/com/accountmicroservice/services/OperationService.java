package com.accountmicroservice.services;

import com.accountmicroservice.models.Account;
import com.accountmicroservice.models.Operation;

import java.util.List;

public interface OperationService {
    List<Operation> getAllOperationsPerAccount(Integer accountId);
    List<Account> getAllAccountsPerUser(Integer userId);
    Operation saveOperation(Operation operation);
}
