package com.lopsit.lopslog.company;

import java.time.LocalDateTime;
import java.util.UUID;

public class Company {
  private final UUID id;
  private final Cnpj cnpj;
  private final String name;
  private final boolean active;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  private Company(
      UUID id,
      Cnpj cnpj,
      String name,
      boolean active,
      LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.id = id;
    this.cnpj = cnpj;
    this.name = name;
    this.active = active;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  // TODO
}
