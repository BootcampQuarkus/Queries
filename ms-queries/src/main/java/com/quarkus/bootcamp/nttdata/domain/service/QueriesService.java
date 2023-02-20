package com.quarkus.bootcamp.nttdata.domain.service;

import com.quarkus.bootcamp.nttdata.domain.entity.*;
import com.quarkus.bootcamp.nttdata.domain.mapper.*;
import com.quarkus.bootcamp.nttdata.infraestructure.resources.*;
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
  @RestClient
  IPaymentApi paymentApi;
  @RestClient
  IShoppingApi shoppingApi;
  @Inject
  AccountMapper aMapper;
  @Inject
  CreditMapper cMapper;
  @Inject
  LineOfCreditMapper locMapper;
  @Inject
  OperationMapper oMapper;
  @Inject
  PaymentShoppingMapper psMapper;

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
            if (p.getOperationType().getId().equals(2L) || (p.getOperationType().getId().equals(3L) && p.getSourceAccount().equals(accountId))) {
              p.setAmount((-1.0D) * p.getAmount());
            }
            return p;
          })
          .toList();
    accountOperation.setOperations(operations);
    return accountOperation;
  }

  public CreditOperation getCredit(Long creditId) {
    Credit credit = cMapper.toDto(creditApi.getById(creditId));
    CreditOperation creditOperation = new CreditOperation();
    creditOperation.setId(credit.getId());
    creditOperation.setAmount(credit.getAmount());
    creditOperation.setBalance(credit.getBalance());
    creditOperation.setPaid(credit.getAmount() - credit.getBalance());
    creditOperation.setDues(credit.getDues());
    creditOperation.setPaymentDueDate(credit.getPaymentDueDate());
    creditOperation.setCustomerId(credit.getCustomerId());
    List<Operation2> operations = paymentApi.getAll(creditId).stream().map(psMapper::paymentDToOperation2).toList();
    creditOperation.setOperations(operations);
    return creditOperation;
  }

  public LineOfCreditOperation getLineOfCredit(Long lineOfCreditId) {
    LineOfCredit lineOfCredit = locMapper.toDto(lineOfCreditApi.getById(lineOfCreditId));
    LineOfCreditOperation lineOfCreditOperation = new LineOfCreditOperation();
    lineOfCreditOperation.setId(lineOfCredit.getId());
    lineOfCreditOperation.setAmount(lineOfCredit.getAmount());
    lineOfCreditOperation.setAvailable(lineOfCredit.getAvailable());
    lineOfCreditOperation.setCosts(lineOfCredit.getCosts());
    lineOfCreditOperation.setClosingDate(lineOfCredit.getClosingDate());
    lineOfCreditOperation.setPaymentDueDate(lineOfCredit.getPaymentDueDate());
    lineOfCreditOperation.setCustomerId(lineOfCredit.getCustomerId());
    List<Operation2> operations = new java.util.ArrayList<>(paymentApi.getAll(lineOfCreditId).stream().map(psMapper::paymentDToOperation2).toList());
    operations.addAll(shoppingApi.getAll(lineOfCreditId)
          .stream()
          .map(psMapper::shoppingDToOperation2)
          .map(
                p -> {
                  p.setAmount((1.0) * p.getAmount());
                  return p;
                }
          )
          .toList());
    lineOfCreditOperation.setOperations(operations);
    return lineOfCreditOperation;
  }
}
