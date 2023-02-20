package com.quarkus.bootcamp.nttdata.domain.mapper;

import com.quarkus.bootcamp.nttdata.domain.entity.OperationType;
import com.quarkus.bootcamp.nttdata.domain.interfaces.IMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.OperationTypeD;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OperationTypeMapper implements IMapper<OperationType, OperationTypeD> {
  @Override
  public OperationTypeD toEntity(OperationType operationType) {
    OperationTypeD operationTypeD = new OperationTypeD();
    operationTypeD.setId(operationType.getId());
    operationTypeD.setName(operationType.getName());
    return operationTypeD;
  }

  @Override
  public OperationType toDto(OperationTypeD operationTypeD) {
    OperationType operationType = new OperationType();
    operationType.setId(operationTypeD.getId());
    operationType.setName(operationTypeD.getName());
    return operationType;
  }
}
