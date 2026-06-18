package com.lopsit.lopslog.vehicle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class FuelConsumptionLogTest {
  private static final UUID tractorUnitId = UUID.randomUUID();
  private static final UUID driverId = UUID.randomUUID();
  private static final LoadStatus loadStatus = LoadStatus.LOADED;
  private static final int distanceKm = 100;
  private static final BigDecimal listersOfFuelConsumed = BigDecimal.TEN;
  private static final int odometer = 100000;
  private static final LocalDateTime pastDate = LocalDateTime.now().minusDays(30L);
  private static final LocalDateTime futureDate = LocalDateTime.now().plusDays(30L);

  @Test
  void create() {
    // valid fuel consumption log
    FuelConsumptionLog log =
        FuelConsumptionLog.create(
            null,
            tractorUnitId,
            driverId,
            pastDate,
            null,
            loadStatus,
            distanceKm,
            listersOfFuelConsumed,
            odometer);
    assertNotNull(log);
    // invalid tractor unit id
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                null,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                odometer));
    // invalid driver id
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                tractorUnitId,
                null,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                odometer));
    // invalid occurred at
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                tractorUnitId,
                driverId,
                null,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                odometer));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                tractorUnitId,
                driverId,
                futureDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                odometer));
    // invalid description
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                tractorUnitId,
                driverId,
                pastDate,
                "",
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                odometer));
    // invalid load status
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                null,
                100,
                listersOfFuelConsumed,
                odometer));
    // invalid distance km
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                null,
                listersOfFuelConsumed,
                odometer));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                0,
                listersOfFuelConsumed,
                odometer));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                -100,
                listersOfFuelConsumed,
                odometer));
    // invalid listers of fuel consumed
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                null,
                odometer));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                BigDecimal.ZERO,
                odometer));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                BigDecimal.valueOf(-100),
                odometer));
    // invalid odometer
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                null));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                0));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                -100000));
  }

  @Test
  void reconstitute() {
    // valid fuel consumption log
    FuelConsumptionLog log =
        FuelConsumptionLog.reconstitute(
            UUID.randomUUID(),
            null,
            tractorUnitId,
            driverId,
            pastDate,
            null,
            loadStatus,
            distanceKm,
            listersOfFuelConsumed,
            odometer,
            pastDate);
    assertNotNull(log);
    // invalid id
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                null,
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                odometer,
                pastDate));
    // invalid tractor unit id
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                null,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                odometer,
                pastDate));
    // invalid driver id
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                null,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                odometer,
                pastDate));
    // invalid occurred at
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                null,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                odometer,
                pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                futureDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                odometer,
                pastDate));
    // invalid description
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                pastDate,
                "",
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                odometer,
                pastDate));
    // invalid load status
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                null,
                distanceKm,
                listersOfFuelConsumed,
                odometer,
                pastDate));
    // invalid distance km
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                null,
                listersOfFuelConsumed,
                odometer,
                pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                0,
                listersOfFuelConsumed,
                odometer,
                pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                -100,
                listersOfFuelConsumed,
                odometer,
                pastDate));
    // invalid listers of fuel consumed
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                null,
                odometer,
                pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                BigDecimal.ZERO,
                odometer,
                pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                BigDecimal.valueOf(-100),
                odometer,
                pastDate));
    // invalid odometer
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                null,
                pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                0,
                pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                -100000,
                pastDate));
    // invalid created at
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                pastDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                odometer,
                null));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                tractorUnitId,
                driverId,
                futureDate,
                null,
                loadStatus,
                distanceKm,
                listersOfFuelConsumed,
                odometer,
                futureDate));
  }
}
