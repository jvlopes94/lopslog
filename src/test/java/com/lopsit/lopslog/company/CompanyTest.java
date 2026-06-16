package com.lopsit.lopslog.company;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class CompanyTest {
  private static final Cnpj cnpj = new Cnpj("45174649000167");
  private static final UUID randomId = UUID.randomUUID();
  private static final LocalDateTime pastDate = LocalDateTime.now().minusDays(30L);
  private static final LocalDateTime futureDate = LocalDateTime.now().plusDays(30L);

  @Test
  void create() {
    // valid company
    Company company = Company.create(cnpj, "Name");
    assertNotNull(company);
    // invalid cnpj
    assertThrows(IllegalArgumentException.class, () -> Company.create(null, "Name"));
    // invalid names
    assertThrows(IllegalArgumentException.class, () -> Company.create(cnpj, null));
    assertThrows(IllegalArgumentException.class, () -> Company.create(cnpj, ""));
    assertThrows(IllegalArgumentException.class, () -> Company.create(cnpj, "\t"));
    assertThrows(IllegalArgumentException.class, () -> Company.create(cnpj, "\n"));
    assertThrows(IllegalArgumentException.class, () -> Company.create(cnpj, "  "));
  }

  @Test
  void reconstitute() {
    // valid company
    Company company = Company.reconstitute(randomId, cnpj, "Name", true, pastDate, pastDate);
    assertNotNull(company);
    // invalid id
    assertThrows(
        IllegalArgumentException.class,
        () -> Company.reconstitute(null, cnpj, "Name", true, pastDate, pastDate));
    // invalid cnpj
    assertThrows(
        IllegalArgumentException.class,
        () -> Company.reconstitute(randomId, null, "Name", true, pastDate, pastDate));
    // invalid name
    assertThrows(
        IllegalArgumentException.class,
        () -> Company.reconstitute(randomId, cnpj, null, true, pastDate, pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () -> Company.reconstitute(randomId, cnpj, "", true, pastDate, pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () -> Company.reconstitute(randomId, cnpj, " ", true, pastDate, pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () -> Company.reconstitute(randomId, cnpj, "\t", true, pastDate, pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () -> Company.reconstitute(randomId, cnpj, "\n", true, pastDate, pastDate));
    // invalid created at
    assertThrows(
        IllegalArgumentException.class,
        () -> Company.reconstitute(randomId, cnpj, "\n", true, null, pastDate));
    assertThrows(
        IllegalArgumentException.class,
        () -> Company.reconstitute(randomId, cnpj, "\n", true, futureDate, pastDate));
    // invalid updated at
    assertThrows(
        IllegalArgumentException.class,
        () -> Company.reconstitute(randomId, cnpj, "\n", true, pastDate, null));
    assertThrows(
        IllegalArgumentException.class,
        () -> Company.reconstitute(randomId, cnpj, "\n", true, pastDate, futureDate));
    assertThrows(
        IllegalArgumentException.class,
        () ->
            Company.reconstitute(
                randomId, cnpj, "\n", true, pastDate, LocalDateTime.now().minusDays(45L)));
  }
}
