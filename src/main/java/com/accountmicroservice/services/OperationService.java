package com.accountmicroservice.services;

import com.accountmicroservice.models.Account;
import com.accountmicroservice.models.Operation;

import java.util.List;

public interface OperationService {
    List<Operation> getAllOperationsPerAccount(String accountId);
    List<Account> getAllAccountsPerUser(String userId);
    Operation saveOperation(Operation operation);
}
