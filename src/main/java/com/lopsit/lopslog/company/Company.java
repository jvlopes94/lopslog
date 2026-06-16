package com.lopsit.lopslog.company;

import java.time.LocalDateTime;
import java.util.Objects;
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
    if (id == null) throw new IllegalArgumentException("id cannot be null");
    if (cnpj == null) throw new IllegalArgumentException("cnpj cannot be null");
    if (name == null || name.isBlank()) throw new IllegalArgumentException("name cannot be blank");
    if (createdAt == null) throw new IllegalArgumentException("createdAt cannot be null");
    if (createdAt.isAfter(LocalDateTime.now()))
      throw new IllegalArgumentException("createdAt cannot be in the future");
    if (updatedAt == null) throw new IllegalArgumentException("updatedAt cannot be null");
    if (updatedAt.isAfter(LocalDateTime.now()))
      throw new IllegalArgumentException("updatedAt cannot be in the future");
    if (createdAt.isAfter(updatedAt))
      throw new IllegalArgumentException("createdAt cannot be after updatedAt");
    this.id = id;
    this.cnpj = cnpj;
    this.name = name;
    this.active = active;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public static Company create(Cnpj cnpj, String name) {
    LocalDateTime now = LocalDateTime.now();
    return new Company(UUID.randomUUID(), cnpj, name, true, now, now);
  }

  public static Company reconstitute(
      UUID id,
      Cnpj cnpj,
      String name,
      boolean active,
      LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    return new Company(id, cnpj, name, active, createdAt, updatedAt);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;

    Company company = (Company) o;
    return Objects.equals(id, company.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
