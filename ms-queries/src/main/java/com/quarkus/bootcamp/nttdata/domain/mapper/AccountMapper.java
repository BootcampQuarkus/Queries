package com.quarkus.bootcamp.nttdata.domain.mapper;

import com.quarkus.bootcamp.nttdata.domain.entity.Account;
import com.quarkus.bootcamp.nttdata.domain.interfaces.IMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.AccountD;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountMapper implements IMapper<Account, AccountD> {
  @Override
  public AccountD toEntity(Account account) {
    AccountD accountD = new AccountD();
    accountD.setId(account.getId());
    accountD.setAmount(account.getAmount());
    accountD.setCustomerId(account.getCustomerId());
    accountD.setCardId(account.getCardId());
    return accountD;
  }

  @Override
  public Account toDto(AccountD accountD) {
    Account account = new Account();
    account.setId(accountD.getId());
    account.setAmount(accountD.getAmount());
    account.setCustomerId(accountD.getCustomerId());
    account.setCardId(accountD.getCardId());
    return account;
  }
}
