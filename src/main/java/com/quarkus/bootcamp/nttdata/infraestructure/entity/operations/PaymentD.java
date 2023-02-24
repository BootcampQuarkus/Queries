package com.quarkus.bootcamp.nttdata.infraestructure.entity.operations;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentD {
  protected Long id;
  protected Double amount;
  protected String description;
  protected Long productId;
  protected String date;
}
