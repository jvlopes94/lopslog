package com.lopsit.lopslog.vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class FuelConsumptionLog {
  private final UUID id;
  private final UUID manifestId;
  private final UUID tractorUnitId;
  private final UUID driverId;
  private final LocalDateTime occurredAt;
  private final String description;
  private final LoadStatus loadStatus;
  private final Integer distanceKm;
  private final BigDecimal litersOfFuelConsumed;
  private final Integer odometer;
  private final LocalDateTime createdAt;

  private FuelConsumptionLog(
      UUID id,
      UUID manifestId,
      UUID tractorUnitId,
      UUID driverId,
      LocalDateTime occurredAt,
      String description,
      LoadStatus loadStatus,
      Integer distanceKm,
      BigDecimal litersOfFuelConsumed,
      Integer odometer,
      LocalDateTime createdAt) {
    if (id == null) throw new IllegalArgumentException("id cannot be null");
    if (tractorUnitId == null) throw new IllegalArgumentException("tractorUnitId cannot be null");
    if (driverId == null) throw new IllegalArgumentException("driverId cannot be null");
    validateOccurredAt(occurredAt);
    if (description != null && description.isBlank())
      throw new IllegalArgumentException("description cannot be blank");
    if (loadStatus == null) throw new IllegalArgumentException("loadStatus cannot be null");
    validateDistanceKm(distanceKm);
    validateListerOfFuelConsumed(litersOfFuelConsumed);
    validateOdometer(odometer);
    validateCreatedAt(createdAt);
    this.id = id;
    this.manifestId = manifestId;
    this.tractorUnitId = tractorUnitId;
    this.driverId = driverId;
    this.occurredAt = occurredAt;
    this.description = description;
    this.loadStatus = loadStatus;
    this.distanceKm = distanceKm;
    this.litersOfFuelConsumed = litersOfFuelConsumed;
    this.odometer = odometer;
    this.createdAt = createdAt;
  }

  public static FuelConsumptionLog create(
      UUID manifestId,
      UUID tractorUnitId,
      UUID driverId,
      LocalDateTime occurredAt,
      String description,
      LoadStatus loadStatus,
      Integer distanceKm,
      BigDecimal litersOfFuelConsumed,
      Integer odometer) {
    return new FuelConsumptionLog(
        UUID.randomUUID(),
        manifestId,
        tractorUnitId,
        driverId,
        occurredAt,
        description,
        loadStatus,
        distanceKm,
        litersOfFuelConsumed,
        odometer,
        LocalDateTime.now());
  }

  public static FuelConsumptionLog reconstitute(
      UUID id,
      UUID manifestId,
      UUID tractorUnitId,
      UUID driverId,
      LocalDateTime occurredAt,
      String description,
      LoadStatus loadStatus,
      Integer distanceKm,
      BigDecimal litersOfFuelConsumed,
      Integer odometer,
      LocalDateTime createdAt) {
    return new FuelConsumptionLog(
        id,
        manifestId,
        tractorUnitId,
        driverId,
        occurredAt,
        description,
        loadStatus,
        distanceKm,
        litersOfFuelConsumed,
        odometer,
        createdAt);
  }

  private static void validateOccurredAt(LocalDateTime occurredAt) {
    if (occurredAt == null) throw new IllegalArgumentException("occurredAt cannot be null");
    if (occurredAt.isAfter(LocalDateTime.now()))
      throw new IllegalArgumentException("occurredAt cannot be in the future");
  }

  private static void validateDistanceKm(Integer distanceKm) {
    if (distanceKm == null) throw new IllegalArgumentException("distanceKm cannot be null");
    if (distanceKm <= 0) throw new IllegalArgumentException("distanceKm cannot be negative");
  }

  private static void validateListerOfFuelConsumed(BigDecimal litersOfFuelConsumed) {
    if (litersOfFuelConsumed == null)
      throw new IllegalArgumentException("litersOfFuelConsumed cannot be null");
    if (litersOfFuelConsumed.compareTo(BigDecimal.ZERO) <= 0)
      throw new IllegalArgumentException("litersOfFuelConsumed cannot be zero or negative");
  }

  private static void validateOdometer(Integer odometer) {
    if (odometer == null) throw new IllegalArgumentException("odometer cannot be null");
    if (odometer <= 0) throw new IllegalArgumentException("odometer cannot be negative");
  }

  private static void validateCreatedAt(LocalDateTime createdAt) {
    if (createdAt == null) throw new IllegalArgumentException("createdAt cannot be null");
    if (createdAt.isAfter(LocalDateTime.now()))
      throw new IllegalArgumentException("createdAt cannot be in the future");
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;

    FuelConsumptionLog that = (FuelConsumptionLog) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
