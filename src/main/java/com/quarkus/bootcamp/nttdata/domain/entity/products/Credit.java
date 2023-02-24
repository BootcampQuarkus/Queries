package com.quarkus.bootcamp.nttdata.domain.entity.products;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Credit {
  protected Long id;
  protected Double amount;
  protected Double balance;
  protected Integer dues;
  protected String paymentDueDate;
  protected Long customerId;
}
