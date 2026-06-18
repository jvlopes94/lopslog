package com.lopsit.lopslog.vehicle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class TractorUnitTest {
  private static final UUID tractorUnitId = UUID.randomUUID();
  private static final UUID companyId = UUID.randomUUID();
  private static final LicensePlate licensePlate = new LicensePlate("JZR4356");
  private static final LocalDateTime pastDate = LocalDateTime.now().minusDays(30L);
  private static final LocalDateTime futureDate = LocalDateTime.now().plusDays(30L);

  @Test
  void create() {
    // valid tractor unit
    TractorUnit tractorUnit = TractorUnit.create(companyId, licensePlate);
    assertNotNull(tractorUnit);
    // invalid company id
    assertThrows(IllegalArgumentException.class, () -> TractorUnit.create(null, licensePlate));
    // invalid license plate
    assertThrows(IllegalArgumentException.class, () -> TractorUnit.create(companyId, null));
  }

  @Test
  void reconstitute() {
    // valid tractor unit
    TractorUnit tractorUnit =
        TractorUnit.reconstitute(tractorUnitId, companyId, licensePlate, pastDate, pastDate);
    assertNotNull(tractorUnit);
    // invalid id
    assertThrows(
        IllegalArgumentException.class,
        () -> TractorUnit.reconstitute(null, companyId, licensePlate, pastDate, pastDate));
    // invalid company id
    assertThrows(
        IllegalArgumentException.class,
        () -> TractorUnit.reconstitute(tractorUnitId, null, licensePlate, pastDate, pastDate));
    // invalid license plate
    assertThrows(
        IllegalArgumentException.class,
        () -> TractorUnit.reconstitute(tractorUnitId, companyId, null, pastDate, pastDate));
    // invalid created at
    assertThrows(
        IllegalArgumentException.class,
        () -> TractorUnit.reconstitute(tractorUnitId, companyId, licensePlate, null, pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            TractorUnit.reconstitute(tractorUnitId, companyId, licensePlate, futureDate, pastDate));
    // invalid updated at
    assertThrows(
        IllegalArgumentException.class,
        () -> TractorUnit.reconstitute(tractorUnitId, companyId, licensePlate, pastDate, null));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            TractorUnit.reconstitute(tractorUnitId, companyId, licensePlate, pastDate, futureDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            TractorUnit.reconstitute(
                tractorUnitId,
                companyId,
                licensePlate,
                pastDate,
                LocalDateTime.now().minusDays(45L)));
  }
}
