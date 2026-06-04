package com.lopsit.lopslog.vehicle;

import java.time.LocalDateTime;
import java.util.UUID;

public class TractorUnit {
  private final UUID id;
  private final UUID companyId;
  private final LicensePlate licensePlate;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  private TractorUnit(
      UUID id,
      UUID companyId,
      LicensePlate licensePlate,
      LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    if (id == null) throw new IllegalArgumentException("id cannot be null");
    if (companyId == null) throw new IllegalArgumentException("companyId cannot be null");
    if (licensePlate == null) throw new IllegalArgumentException("licensePlate cannot be null");
    if (createdAt == null) throw new IllegalArgumentException("createdAt cannot be null");
    if (createdAt.isAfter(LocalDateTime.now()))
      throw new IllegalArgumentException("createdAt cannot be in the future");
    if (updatedAt == null) throw new IllegalArgumentException("updatedAt cannot be null");
    if (updatedAt.isAfter(LocalDateTime.now()))
      throw new IllegalArgumentException("updatedAt cannot be in the future");
    if (createdAt.isAfter(updatedAt))
      throw new IllegalArgumentException("createdAt cannot be after updatedAt");
    this.id = id;
    this.companyId = companyId;
    this.licensePlate = licensePlate;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public static TractorUnit create(UUID companyId, LicensePlate licensePlate) {
    LocalDateTime now = LocalDateTime.now();
    return new TractorUnit(UUID.randomUUID(), companyId, licensePlate, now, now);
  }

  public static TractorUnit reconstitute(
      UUID id,
      UUID companyId,
      LicensePlate licensePlate,
      LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    return new TractorUnit(id, companyId, licensePlate, createdAt, updatedAt);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;

    TractorUnit that = (TractorUnit) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
