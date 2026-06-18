package com.lopsit.lopslog.vehicle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class TractorUnitTest {

  @Test
  void create() {
    // valid tractor unit
    TractorUnit tractorUnit = TractorUnit.create(UUID.randomUUID(), new LicensePlate("JZR4356"));
    assertNotNull(tractorUnit);
    // invalid company id
    assertThrows(
        IllegalArgumentException.class,
        () -> TractorUnit.create(null, new LicensePlate("JZR4356")));
    // invalid license plate
    assertThrows(IllegalArgumentException.class, () -> TractorUnit.create(UUID.randomUUID(), null));
  }

  @Test
  void reconstitute() {
    LocalDateTime pastDate = LocalDateTime.now().minusDays(30L);
    LocalDateTime futureDate = LocalDateTime.now().plusDays(30L);
    LicensePlate licensePlate = new LicensePlate("JZR4356");
    // valid tractor unit
    TractorUnit tractorUnit =
        TractorUnit.reconstitute(
            UUID.randomUUID(), UUID.randomUUID(), licensePlate, pastDate, pastDate);
    assertNotNull(tractorUnit);
    // invalid id
    assertThrows(
        IllegalArgumentException.class,
        () -> TractorUnit.reconstitute(null, UUID.randomUUID(), licensePlate, pastDate, pastDate));
    // invalid company id
    assertThrows(
        IllegalArgumentException.class,
        () -> TractorUnit.reconstitute(UUID.randomUUID(), null, licensePlate, pastDate, pastDate));
    // invalid license plate
    assertThrows(
        IllegalArgumentException.class,
        () ->
            TractorUnit.reconstitute(
                UUID.randomUUID(), UUID.randomUUID(), null, pastDate, pastDate));
    // invalid created at
    assertThrows(
        IllegalArgumentException.class,
        () ->
            TractorUnit.reconstitute(
                UUID.randomUUID(), UUID.randomUUID(), licensePlate, null, pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            TractorUnit.reconstitute(
                UUID.randomUUID(), UUID.randomUUID(), licensePlate, futureDate, pastDate));
    // invalid updated at
    assertThrows(
        IllegalArgumentException.class,
        () ->
            TractorUnit.reconstitute(
                UUID.randomUUID(), UUID.randomUUID(), licensePlate, pastDate, null));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            TractorUnit.reconstitute(
                UUID.randomUUID(), UUID.randomUUID(), licensePlate, pastDate, futureDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            TractorUnit.reconstitute(
                UUID.randomUUID(),
                UUID.randomUUID(),
                licensePlate,
                pastDate,
                LocalDateTime.now().minusDays(45L)));
  }
}
