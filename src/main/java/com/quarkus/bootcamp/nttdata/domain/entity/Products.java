package com.quarkus.bootcamp.nttdata.domain.entity;

import com.quarkus.bootcamp.nttdata.domain.entity.products.Account;
import com.quarkus.bootcamp.nttdata.domain.entity.products.Credit;
import com.quarkus.bootcamp.nttdata.domain.entity.products.LineOfCredit;
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
