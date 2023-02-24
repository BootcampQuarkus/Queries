package com.quarkus.bootcamp.nttdata.domain.entity.products;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {
  protected Long id;
  protected Double amount;
  protected Long customerId;
  protected Long cardId;
}
