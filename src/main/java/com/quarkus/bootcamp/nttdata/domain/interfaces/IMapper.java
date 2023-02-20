package com.quarkus.bootcamp.nttdata.domain.interfaces;

public interface IMapper<T, U> {
  U toEntity(T t);

  T toDto(U u);
}
