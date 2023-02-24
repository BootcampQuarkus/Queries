package com.quarkus.bootcamp.nttdata.domain.entity.operations;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Operation2 {
  protected Long id;
  protected Double amount;
  protected String description;
  protected Long productId;
  protected String date;
}
