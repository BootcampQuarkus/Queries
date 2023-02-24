package com.quarkus.bootcamp.nttdata.domain.entity;

import com.quarkus.bootcamp.nttdata.domain.entity.operations.Operation2;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CreditOperation {
  protected Long id;
  /**
   * Monto aprobado del credito al momento de crear el producto.
   */
  protected Double amount;
  /**
   * El saldo por pagar
   */
  protected Double balance;
  /**
   * Monto pagado
   */
  protected Double paid;
  /**
   * Número de cuotas
   */
  protected Integer dues;
  /**
   * Fecha de pago de las cuotas (dd-mm).
   */
  protected String paymentDueDate;
  /**
   * Id del cliente al que le pertenece el producto
   */
  protected Long customerId;
  protected List<Operation2> operations;
}
