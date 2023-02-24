package com.quarkus.bootcamp.nttdata.domain.service;

import com.quarkus.bootcamp.nttdata.domain.entity.*;
import com.quarkus.bootcamp.nttdata.domain.entity.operations.Operation;
import com.quarkus.bootcamp.nttdata.domain.entity.operations.Operation2;
import com.quarkus.bootcamp.nttdata.domain.entity.products.Account;
import com.quarkus.bootcamp.nttdata.domain.entity.products.Credit;
import com.quarkus.bootcamp.nttdata.domain.entity.products.LineOfCredit;
import com.quarkus.bootcamp.nttdata.domain.mapper.*;
import com.quarkus.bootcamp.nttdata.infraestructure.resources.*;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
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

  public Uni<Products> getAll(Long customerId) {
    Uni<List<Account>> accountList = accountApi.getAll(customerId).onItem().transform(p -> p.stream().map(q -> aMapper.toDto(q)).toList());
    Uni<List<Credit>> creditList = creditApi.getAll(customerId).onItem().transform(p -> p.stream().map(q -> cMapper.toDto(q)).toList());
    Uni<List<LineOfCredit>> lineOfCreditList = lineOfCreditApi.getAll(customerId).onItem().transform(p -> p.stream().map(q -> locMapper.toDto(q)).toList());

    return Uni.combine()
          .all()
          .unis(accountList, creditList, lineOfCreditList)
          .combinedWith(lisp -> {
            List<Account> aList = (List<Account>) lisp.get(0);
            List<Credit> cList = (List<Credit>) lisp.get(1);
            List<LineOfCredit> locList = (List<LineOfCredit>) lisp.get(2);
            Products products = new Products();
            products.setAccounts(aList);
            products.setCredits(cList);
            products.setLinesOfCredit(locList);
            return products;
          });
  }

  public Uni<AccountOperation> getAccount(Long accountId) {
    Uni<List<Operation>> operationList = operationApi.getAll(accountId)
          .onItem().transform(p -> p.stream().map(q -> {
            Operation s = oMapper.toDto(q);
            if (s.getOperationType().getId().equals(2L) || (s.getOperationType().getId().equals(3L) && s.getSourceAccount().equals(accountId))) {
              s.setAmount((-1.0D) * s.getAmount());
            }
            return s;
          }).toList());
    Uni<AccountOperation> accountUni = accountApi
          .getById(accountId)
          .onItem()
          .transform(p -> {
            AccountOperation accountOperation = new AccountOperation();
            accountOperation.setId(p.getId());
            accountOperation.setAmount(p.getAmount());
            accountOperation.setCustomerId(p.getCustomerId());
            accountOperation.setCardId(p.getCardId());
            return accountOperation;
          });

    return Uni.combine().all().unis(operationList, accountUni).combinedWith(lisp -> {
      List<Operation> opList = (List<Operation>) lisp.get(0);
      AccountOperation accountOperation = (AccountOperation) lisp.get(1);
      accountOperation.setOperations(opList);
      return accountOperation;
    });
  }

  public Uni<CreditOperation> getCredit(Long creditId) {
    Uni<List<Operation2>> operationList = paymentApi.getAll(creditId)
          .onItem().transform(p -> p.stream().map(q -> {
            Operation2 s = psMapper.paymentDToOperation2(q);
            return s;
          }).toList());
    Uni<CreditOperation> operationUni = creditApi
          .getById(creditId)
          .onItem()
          .transform(p -> {
            CreditOperation creditOperation = new CreditOperation();
            creditOperation.setId(p.getId());
            creditOperation.setAmount(p.getAmount());
            creditOperation.setBalance(p.getBalance());
            creditOperation.setPaid(p.getAmount() - p.getBalance());
            creditOperation.setDues(p.getDues());
            creditOperation.setPaymentDueDate(p.getPaymentDueDate());
            creditOperation.setCustomerId(p.getCustomerId());
            return creditOperation;
          });

    return Uni.combine().all().unis(operationList, operationUni).combinedWith(lisp -> {
      List<Operation2> opList = (List<Operation2>) lisp.get(0);
      CreditOperation creditOperation = (CreditOperation) lisp.get(1);
      creditOperation.setOperations(opList);
      return creditOperation;
    });
  }

  public Uni<LineOfCreditOperation> getLineOfCredit(Long lineOfCreditId) {
    Uni<List<Operation2>> paymentList = paymentApi.getAll(lineOfCreditId)
          .onItem()
          .transform(p -> p.stream().map(q -> {
            Operation2 s = psMapper.paymentDToOperation2(q);
            return s;
          }).toList());
    Uni<List<Operation2>> shoppingList = shoppingApi.getAll(lineOfCreditId)
          .onItem()
          .transform(p -> p.stream().map(q -> {
            Operation2 s = psMapper.shoppingDToOperation2(q);
            s.setAmount((1.0) * s.getAmount());
            return s;
          }).toList());
    Uni<LineOfCreditOperation> lineOfCreditOperationUni = lineOfCreditApi
          .getById(lineOfCreditId)
          .onItem()
          .transform(p -> {
            LineOfCreditOperation lineOfCreditOperation = new LineOfCreditOperation();
            lineOfCreditOperation.setId(p.getId());
            lineOfCreditOperation.setAmount(p.getAmount());
            lineOfCreditOperation.setAvailable(p.getAvailable());
            lineOfCreditOperation.setCosts(p.getCosts());
            lineOfCreditOperation.setClosingDate(p.getClosingDate());
            lineOfCreditOperation.setPaymentDueDate(p.getPaymentDueDate());
            lineOfCreditOperation.setCustomerId(p.getCustomerId());
            return lineOfCreditOperation;
          });

    return Uni.combine().all().unis(paymentList, shoppingList, lineOfCreditOperationUni).combinedWith(lisp -> {
      List<Operation2> pList = (List<Operation2>) lisp.get(0);
      List<Operation2> sList = (List<Operation2>) lisp.get(1);
      LineOfCreditOperation lineOfCreditOperation = (LineOfCreditOperation) lisp.get(2);
      List<Operation2> lista = new ArrayList<>();
      lista.addAll(pList);
      lista.addAll(sList);
      lineOfCreditOperation.setOperations(lista);
      return lineOfCreditOperation;
    });
  }
}
