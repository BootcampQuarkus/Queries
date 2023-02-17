package com.quarkus.bootcamp.nttdata.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LineOfCredit {
  protected Long id;
  protected Double amount;
  protected Double available;
  protected Double costs;
  protected String closingDate;
  protected String paymentDueDate;
  protected Long customerId;
}
