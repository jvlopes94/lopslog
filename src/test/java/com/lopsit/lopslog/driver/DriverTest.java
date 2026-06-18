package com.lopsit.lopslog.driver;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class DriverTest {
  private static final Cnh cnh = Cnh.of("37520806319");
  private static final Cpf cpf = Cpf.of("21444373005");
  private static final UUID randomId = UUID.randomUUID();
  private static final UUID companyId = UUID.randomUUID();
  private static final LocalDateTime pastDate = LocalDateTime.now().minusDays(30L);
  private static final LocalDateTime futureDate = LocalDateTime.now().plusDays(30L);

  @Test
  void create() {
    // valid driver
    Driver driver = Driver.create("name", cnh, cpf, companyId);
    assertNotNull(driver);
    // invalid name
    assertThrows(IllegalArgumentException.class, () -> Driver.create(null, cnh, cpf, companyId));
    assertThrows(IllegalArgumentException.class, () -> Driver.create("", cnh, cpf, companyId));
    // invalid company id
    assertThrows(IllegalArgumentException.class, () -> Driver.create("name", cnh, cpf, null));
  }

  @Test
  void reconstitute() {
    // valid driver
    Driver driver = Driver.reconstitute(randomId, "name", cnh, cpf, companyId, pastDate, pastDate);
    assertNotNull(driver);
    // invalid id
    assertThrows(
        IllegalArgumentException.class,
        () -> Driver.reconstitute(null, "name", cnh, cpf, randomId, pastDate, pastDate));
    // invalid name
    assertThrows(
        IllegalArgumentException.class,
        () -> Driver.reconstitute(randomId, null, cnh, cpf, randomId, pastDate, pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () -> Driver.reconstitute(randomId, "", cnh, cpf, randomId, pastDate, pastDate));
    // invalid company id
    assertThrows(
        IllegalArgumentException.class,
        () -> Driver.reconstitute(randomId, "name", cnh, cpf, null, pastDate, pastDate));
    // invalid created at
    assertThrows(
        IllegalArgumentException.class,
        () -> Driver.reconstitute(randomId, null, cnh, cpf, randomId, null, pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () -> Driver.reconstitute(randomId, "name", cnh, cpf, randomId, futureDate, pastDate));
    // invalid updated at
    assertThrows(
        IllegalArgumentException.class,
        () -> Driver.reconstitute(randomId, null, cnh, cpf, randomId, pastDate, null));
    assertThrows(
        IllegalArgumentException.class,
        () -> Driver.reconstitute(randomId, "name", cnh, cpf, randomId, pastDate, futureDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            Driver.reconstitute(
                randomId,
                "name",
                cnh,
                cpf,
                randomId,
                pastDate,
                LocalDateTime.now().minusDays(45L)));
  }
}
