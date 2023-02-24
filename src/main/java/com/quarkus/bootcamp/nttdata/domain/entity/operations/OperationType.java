package com.quarkus.bootcamp.nttdata.domain.entity.operations;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OperationType {
  protected Long id;
  protected String name;
}
