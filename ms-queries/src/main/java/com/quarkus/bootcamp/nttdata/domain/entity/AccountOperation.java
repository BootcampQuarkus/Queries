package com.quarkus.bootcamp.nttdata.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AccountOperation {
  protected Long id;
  protected Double amount;
  protected Long customerId;;
  protected Long cardId;
  protected List<Operation> operations;
}
