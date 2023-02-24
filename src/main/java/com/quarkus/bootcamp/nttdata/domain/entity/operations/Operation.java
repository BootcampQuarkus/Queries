package com.quarkus.bootcamp.nttdata.domain.entity.operations;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Operation {
  protected Long id;
  protected Double amount;
  protected String description;
  protected Long sourceAccount;
  protected Long destinationAccount;
  protected OperationType operationType;
  protected String date;
}
