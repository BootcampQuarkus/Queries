package com.quarkus.bootcamp.nttdata.domain.mapper;

import com.quarkus.bootcamp.nttdata.domain.entity.Operation2;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.PaymentD;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.ShoppingD;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentShoppingMapper {
  public Operation2 paymentDToOperation2(PaymentD paymentD) {
    Operation2 operation2 = new Operation2();
    operation2.setId(paymentD.getId());
    operation2.setAmount(paymentD.getAmount());
    operation2.setDescription(paymentD.getDescription());
    operation2.setProductId(paymentD.getProductId());
    operation2.setDate(paymentD.getDate());
    return operation2;
  }

  public Operation2 shoppingDToOperation2(ShoppingD shoppingD) {
    Operation2 operation2 = new Operation2();
    operation2.setId(shoppingD.getId());
    operation2.setAmount(shoppingD.getAmount());
    operation2.setDescription(shoppingD.getDescription());
    operation2.setProductId(shoppingD.getProductId());
    operation2.setDate(shoppingD.getDate());
    return operation2;
  }

  public PaymentD toPaymentD(Operation2 operation2) {
    PaymentD paymentD = new PaymentD();
    paymentD.setId(operation2.getId());
    paymentD.setAmount(operation2.getAmount());
    paymentD.setDescription(operation2.getDescription());
    paymentD.setProductId(operation2.getProductId());
    paymentD.setDate(operation2.getDate());
    return paymentD;
  }

  public ShoppingD toShopping(Operation2 operation2) {
    ShoppingD shoppingD = new ShoppingD();
    shoppingD.setId(operation2.getId());
    shoppingD.setAmount(operation2.getAmount());
    shoppingD.setDescription(operation2.getDescription());
    shoppingD.setProductId(operation2.getProductId());
    shoppingD.setDate(operation2.getDate());
    return shoppingD;
  }
}
