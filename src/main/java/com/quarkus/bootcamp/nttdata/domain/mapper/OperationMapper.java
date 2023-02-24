package com.quarkus.bootcamp.nttdata.domain.mapper;

import com.quarkus.bootcamp.nttdata.domain.entity.operations.Operation;
import com.quarkus.bootcamp.nttdata.domain.interfaces.IMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.operations.OperationD;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OperationMapper implements IMapper<Operation, OperationD> {
  @Inject
  OperationTypeMapper mapper;

  @Override
  public OperationD toEntity(Operation operation) {
    OperationD operationD = new OperationD();
    operationD.setId(operation.getId());
    operationD.setAmount(operation.getAmount());
    operationD.setDescription(operation.getDescription());
    operationD.setSourceAccount(operation.getSourceAccount());
    operationD.setDestinationAccount(operation.getDestinationAccount());
    operationD.setOperationType(mapper.toEntity(operation.getOperationType()));
    return operationD;
  }

  @Override
  public Operation toDto(OperationD operationD) {
    Operation operation = new Operation();
    operation.setId(operationD.getId());
    operation.setAmount(operationD.getAmount());
    operation.setDescription(operationD.getDescription());
    operation.setSourceAccount(operationD.getSourceAccount());
    operation.setDestinationAccount(operationD.getDestinationAccount());
    operation.setOperationType(mapper.toDto(operationD.getOperationType()));
    operation.setDate(operationD.getDate());
    return operation;
  }
}
