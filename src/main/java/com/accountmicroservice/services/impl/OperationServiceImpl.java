package com.accountmicroservice.services.impl;

import com.accountmicroservice.models.Account;
import com.accountmicroservice.models.Operation;
import com.accountmicroservice.repo.AccountRepo;
import com.accountmicroservice.repo.OperationRepo;
import com.accountmicroservice.services.OperationService;
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
    public List<Operation> getAllOperationsPerAccount(Integer accountId) {
        return operationRepo.findAllOperationsByAccount(accountId);
    }

    @Override
    public List<Account> getAllAccountsPerUser(Integer userId) {
        return accountRepo.getAllAccountsPerUser(userId);
    }

    @Override
    public Operation saveOperation(Operation operation) {
        if (operation.getDate() == null)
            operation.setDate(new Date());

        return operationRepo.save(operation);
    }
}
