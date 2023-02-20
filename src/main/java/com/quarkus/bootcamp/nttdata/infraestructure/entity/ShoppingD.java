package com.quarkus.bootcamp.nttdata.infraestructure.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShoppingD {
  protected Long id;
  protected Double amount;
  protected String description;
  protected Long productId;
  protected String date;
}
