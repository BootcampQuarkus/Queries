package com.quarkus.bootcamp.nttdata.domain.mapper;

import com.quarkus.bootcamp.nttdata.domain.entity.products.Credit;
import com.quarkus.bootcamp.nttdata.domain.interfaces.IMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.products.CreditD;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreditMapper implements IMapper<Credit, CreditD> {
  @Override
  public CreditD toEntity(Credit credit) {
    CreditD creditD = new CreditD();
    creditD.setId(credit.getId());
    creditD.setAmount(credit.getAmount());
    creditD.setBalance(credit.getBalance());
    creditD.setDues(credit.getDues());
    creditD.setPaymentDueDate(credit.getPaymentDueDate());
    creditD.setCustomerId(credit.getCustomerId());
    return creditD;
  }

  @Override
  public Credit toDto(CreditD creditD) {
    Credit credit = new Credit();
    credit.setId(creditD.getId());
    credit.setAmount(creditD.getAmount());
    credit.setBalance(creditD.getBalance());
    credit.setDues(creditD.getDues());
    credit.setPaymentDueDate(creditD.getPaymentDueDate());
    credit.setCustomerId(creditD.getCustomerId());
    return credit;
  }
}
