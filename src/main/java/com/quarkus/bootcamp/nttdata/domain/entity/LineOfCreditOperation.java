package com.quarkus.bootcamp.nttdata.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LineOfCreditOperation {
  protected Long id;
  /**
   * Monto aprobado del credito al momento de crear el producto.
   */
  protected Double amount;
  /**
   * Monto disponible
   */
  protected Double available;
  /**
   * Monto por pagar
   */
  protected Double costs;
  /**
   * Número de cuotas
   */
  protected String closingDate;
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
