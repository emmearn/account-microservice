package com.accountmicroservice.demoaccount.services;

import com.accountmicroservice.demoaccount.models.Account;
import com.accountmicroservice.demoaccount.models.Operation;

import java.util.List;

public interface OperationService {
    List<Operation> getAllOperationsPerAccount(String accountId);
    List<Account> getAllAccountsPerUser(String userId);
    Operation saveOperation(Operation operation);
}
