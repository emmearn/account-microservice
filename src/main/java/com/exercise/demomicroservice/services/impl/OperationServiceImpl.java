package com.exercise.demomicroservice.services.impl;

import com.exercise.demomicroservice.models.Account;
import com.exercise.demomicroservice.models.Operation;
import com.exercise.demomicroservice.services.OperationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    @Override
    public List<Operation> getAllOperationsPerAccount(String accountId) {

    }

    @Override
    public List<Account> getAllAccountsPerUser(String userId) {

    }

    @Override
    public Operation saveOperation(Operation operation) {

    }
}
