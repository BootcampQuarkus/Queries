package com.quarkus.bootcamp.nttdata.infraestructure.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OperationD {
  protected Long id;
  protected Double amount;
  protected String description;
  protected Long sourceAccount;
  protected Long destinationAccount;
  protected OperationTypeD operationType;
  protected String date;
}
