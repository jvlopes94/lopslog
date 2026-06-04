package com.lopsit.lopslog.vehicle;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class FuelConsumptionLogTest {

  @Test
  void create() {
    LocalDateTime pastDate = LocalDateTime.now().minusDays(30L);
    LocalDateTime futureDate = LocalDateTime.now().plusDays(30L);
    // invalid tractor unit id
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                null,
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                100000));
    // invalid driver id
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                UUID.randomUUID(),
                null,
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                100000));
    // invalid occurred at
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                null,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                100000));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                futureDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                100000));
    // invalid description
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                "",
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                100000));
    // invalid load status
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                null,
                100,
                BigDecimal.TEN,
                100000));
    // invalid distance km
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                null,
                BigDecimal.TEN,
                100000));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                0,
                BigDecimal.TEN,
                100000));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                -100,
                BigDecimal.TEN,
                100000));
    // invalid listers of fuel consumed
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                null,
                100000));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.ZERO,
                100000));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.valueOf(-100),
                100000));
    // invalid odometer
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                null));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                0));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.create(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                -100000));
  }

  @Test
  void reconstitute() {
    LocalDateTime pastDate = LocalDateTime.now().minusDays(30L);
    LocalDateTime futureDate = LocalDateTime.now().plusDays(30L);
    // invalid id
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                null,
                null,
                null,
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                100000,
                pastDate));
    // invalid tractor unit id
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                null,
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                100000,
                pastDate));
    // invalid driver id
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                null,
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                100000,
                pastDate));
    // invalid occurred at
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                null,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                100000,
                pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                futureDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                100000,
                pastDate));
    // invalid description
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                "",
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                100000,
                pastDate));
    // invalid load status
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                null,
                100,
                BigDecimal.TEN,
                100000,
                pastDate));
    // invalid distance km
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                null,
                BigDecimal.TEN,
                100000,
                pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                0,
                BigDecimal.TEN,
                100000,
                pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                -100,
                BigDecimal.TEN,
                100000,
                pastDate));
    // invalid listers of fuel consumed
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                null,
                100000,
                pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.ZERO,
                100000,
                pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.valueOf(-100),
                100000,
                pastDate));
    // invalid odometer
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                null,
                pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                0,
                pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                -100000,
                pastDate));
    // invalid created at
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                pastDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                100000,
                null));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            FuelConsumptionLog.reconstitute(
                UUID.randomUUID(),
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                futureDate,
                null,
                LoadStatus.LOADED,
                100,
                BigDecimal.TEN,
                100000,
                futureDate));
  }
}
