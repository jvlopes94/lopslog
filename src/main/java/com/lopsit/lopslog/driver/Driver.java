package com.lopsit.lopslog.driver;

import java.time.LocalDateTime;
import java.util.UUID;

public class Driver {
  private final UUID id;
  private final String name;
  private final Cnh cnh;
  private final Cpf cpf;
  private final UUID companyId;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  private Driver(
      UUID id,
      String name,
      Cnh cnh,
      Cpf cpf,
      UUID companyId,
      LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    if (id == null) throw new IllegalArgumentException("id cannot be null");
    if (name == null || name.isBlank()) throw new IllegalArgumentException("name cannot be blank");
    if (companyId == null) throw new IllegalArgumentException("companyId cannot be null");
    if (createdAt == null) throw new IllegalArgumentException("createdAt cannot be null");
    if (createdAt.isAfter(LocalDateTime.now()))
      throw new IllegalArgumentException("createdAt cannot be in the future");
    if (updatedAt == null) throw new IllegalArgumentException("updatedAt cannot be null");
    if (updatedAt.isAfter(LocalDateTime.now()))
      throw new IllegalArgumentException("updatedAt cannot be in the future");
    if (createdAt.isAfter(updatedAt))
      throw new IllegalArgumentException("createdAt cannot be after updatedAt");
    this.id = id;
    this.name = name;
    this.cnh = cnh;
    this.cpf = cpf;
    this.companyId = companyId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public static Driver create(String name, Cnh cnh, Cpf cpf, UUID companyId) {
    LocalDateTime now = LocalDateTime.now();
    return new Driver(UUID.randomUUID(), name, cnh, cpf, companyId, now, now);
  }

  public static Driver reconstitute(
      UUID id,
      String name,
      Cnh cnh,
      Cpf cpf,
      UUID companyId,
      LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    return new Driver(id, name, cnh, cpf, companyId, createdAt, updatedAt);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;

    Driver driver = (Driver) o;
    return id.equals(driver.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
