package com.quarkus.bootcamp.nttdata.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Products {
  List<Account> accounts;
  List<Credit> credits;
  List<LineOfCredit> linesOfCredit;
}
