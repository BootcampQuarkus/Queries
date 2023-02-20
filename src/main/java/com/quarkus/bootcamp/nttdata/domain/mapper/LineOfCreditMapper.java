package com.quarkus.bootcamp.nttdata.domain.mapper;

import com.quarkus.bootcamp.nttdata.domain.entity.LineOfCredit;
import com.quarkus.bootcamp.nttdata.domain.interfaces.IMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.LineOfCreditD;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LineOfCreditMapper implements IMapper<LineOfCredit, LineOfCreditD> {
  @Override
  public LineOfCreditD toEntity(LineOfCredit lineOfCredit) {
    LineOfCreditD lineOfCreditD = new LineOfCreditD();
    lineOfCreditD.setId(lineOfCredit.getId());
    lineOfCreditD.setAmount(lineOfCredit.getAmount());
    lineOfCreditD.setAvailable(lineOfCredit.getAvailable());
    lineOfCreditD.setCosts(lineOfCredit.getCosts());
    lineOfCreditD.setClosingDate(lineOfCredit.getClosingDate());
    lineOfCreditD.setPaymentDueDate(lineOfCredit.getPaymentDueDate());
    lineOfCreditD.setCustomerId(lineOfCredit.getCustomerId());
    return lineOfCreditD;
  }

  @Override
  public LineOfCredit toDto(LineOfCreditD lineOfCreditD) {
    LineOfCredit lineOfCredit = new LineOfCredit();
    lineOfCredit.setId(lineOfCreditD.getId());
    lineOfCredit.setAmount(lineOfCreditD.getAmount());
    lineOfCredit.setAvailable(lineOfCreditD.getAvailable());
    lineOfCredit.setCosts(lineOfCreditD.getCosts());
    lineOfCredit.setClosingDate(lineOfCreditD.getClosingDate());
    lineOfCredit.setPaymentDueDate(lineOfCreditD.getPaymentDueDate());
    lineOfCredit.setCustomerId(lineOfCreditD.getCustomerId());
    return lineOfCredit;
  }
}
