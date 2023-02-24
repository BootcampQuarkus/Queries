package com.quarkus.bootcamp.nttdata.infraestructure.entity.products;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditD {
  protected Long id;
  protected Double amount;
  protected Double balance;
  protected Integer dues;
  protected String paymentDueDate;
  protected Long customerId;
}
