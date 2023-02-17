package com.quarkus.bootcamp.nttdata.domain.service;

import com.quarkus.bootcamp.nttdata.domain.entity.Account;
import com.quarkus.bootcamp.nttdata.domain.entity.AccountOperation;
import com.quarkus.bootcamp.nttdata.domain.entity.Operation;
import com.quarkus.bootcamp.nttdata.domain.entity.Products;
import com.quarkus.bootcamp.nttdata.domain.mapper.AccountMapper;
import com.quarkus.bootcamp.nttdata.domain.mapper.CreditMapper;
import com.quarkus.bootcamp.nttdata.domain.mapper.LineOfCreditMapper;
import com.quarkus.bootcamp.nttdata.domain.mapper.OperationMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.OperationD;
import com.quarkus.bootcamp.nttdata.infraestructure.resources.IAccountApi;
import com.quarkus.bootcamp.nttdata.infraestructure.resources.ICreditApi;
import com.quarkus.bootcamp.nttdata.infraestructure.resources.ILineOfCreditApi;
import com.quarkus.bootcamp.nttdata.infraestructure.resources.IOperationApi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class QueriesService {
  @RestClient
  IAccountApi accountApi;
  @RestClient
  ILineOfCreditApi lineOfCreditApi;
  @RestClient
  ICreditApi creditApi;
  @RestClient
  IOperationApi operationApi;
  @Inject
  AccountMapper aMapper;
  @Inject
  CreditMapper cMapper;
  @Inject
  LineOfCreditMapper locMapper;
  @Inject
  OperationMapper oMapper;

  public Products getAll(Long customerId) {
    Products products = new Products();
    products.setAccounts(accountApi.getAll(customerId).stream().map(aMapper::toDto).toList());
    products.setCredits(creditApi.getAll(customerId).stream().map(cMapper::toDto).toList());
    products.setLinesOfCredit(lineOfCreditApi.getAll(customerId).stream().map(locMapper::toDto).toList());
    return products;
  }

  public AccountOperation getAccount(Long accountId) {
    Account account = aMapper.toDto(accountApi.getById(accountId));
    AccountOperation accountOperation = new AccountOperation();
    accountOperation.setId(account.getId());
    accountOperation.setAmount(account.getAmount());
    accountOperation.setCustomerId(account.getCustomerId());
    accountOperation.setCardId(account.getCardId());
    List<Operation> operations = operationApi.getAll(accountId).stream().map(oMapper::toDto).toList();
    operations = operations.stream()
          .map(p -> {
            if(p.getOperationType().getId().equals(2L) || (p.getOperationType().getId().equals(3L) && p.getSourceAccount().equals(accountId))){
              p.setAmount((-1.0D)*p.getAmount());
            }
            return p;
          })
          .toList();
    accountOperation.setOperations(operations);
    return accountOperation;
  }

  public Products getCredit(Long customerId) {
    Products products = new Products();
    products.setAccounts(accountApi.getAll(customerId).stream().map(aMapper::toDto).toList());
    products.setCredits(creditApi.getAll(customerId).stream().map(cMapper::toDto).toList());
    products.setLinesOfCredit(lineOfCreditApi.getAll(customerId).stream().map(locMapper::toDto).toList());
    return products;
  }

  public Products getLineOfCredit(Long customerId) {
    Products products = new Products();
    products.setAccounts(accountApi.getAll(customerId).stream().map(aMapper::toDto).toList());
    products.setCredits(creditApi.getAll(customerId).stream().map(cMapper::toDto).toList());
    products.setLinesOfCredit(lineOfCreditApi.getAll(customerId).stream().map(locMapper::toDto).toList());
    return products;
  }
}
