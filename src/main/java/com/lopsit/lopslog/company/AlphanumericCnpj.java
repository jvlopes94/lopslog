package com.lopsit.lopslog.company;

import java.util.Objects;

public class AlphanumericCnpj {
  private final String value;

  public AlphanumericCnpj(String value) {
    validate(value);
    this.value = value;
  }

  private void validate(String value) {
    if (value == null) throw new IllegalArgumentException("value cannot be null");
    if (value.isBlank()) throw new IllegalArgumentException("value cannot be blank");
    if (value.length() != 14) throw new IllegalArgumentException("value must have 14 characters");
    // TODO
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;

    AlphanumericCnpj cnpj = (AlphanumericCnpj) o;
    return Objects.equals(value, cnpj.value);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }
}
