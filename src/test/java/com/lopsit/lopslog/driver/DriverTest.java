package com.lopsit.lopslog.driver;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class DriverTest {

  @Test
  void create() {
    Cnh cnh = new Cnh();
    Cpf cpf = new Cpf();
    UUID companyId = UUID.randomUUID();
    // invalid name
    assertThrows(IllegalArgumentException.class, () -> Driver.create(null, cnh, cpf, companyId));
    assertThrows(IllegalArgumentException.class, () -> Driver.create("", cnh, cpf, companyId));
    // invalid company id
    assertThrows(IllegalArgumentException.class, () -> Driver.create("name", cnh, cpf, null));
  }

  @Test
  void reconstitute() {
    Cnh cnh = new Cnh();
    Cpf cpf = new Cpf();
    UUID randomId = UUID.randomUUID();
    LocalDateTime pastDate = LocalDateTime.now().minusDays(30L);
    LocalDateTime futureDate = LocalDateTime.now().plusDays(30L);
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
  }
}
