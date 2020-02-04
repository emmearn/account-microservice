package com.exercise.demomicroservice.services.impl;

import com.exercise.demomicroservice.models.Account;
import com.exercise.demomicroservice.models.Operation;
import com.exercise.demomicroservice.repo.AccountRepo;
import com.exercise.demomicroservice.repo.OperationRepo;
import com.exercise.demomicroservice.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service @Transactional
public class OperationServiceImpl implements OperationService {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    OperationRepo operationRepo;

    @Override
    public List<Operation> getAllOperationsPerAccount(String accountId) {
        return operationRepo.findAllOperationsByAccount(accountId);
    }

    @Override
    public List<Account> getAllAccountsPerUser(String userId) {
        return accountRepo.getAllAccountsPerUser(userId);
    }

    @Override
    public Operation saveOperation(Operation operation) {
        if (operation.getDate() == null)
            operation.setDate(new Date());

        return operationRepo.save(operation);
    }
}
