package com.lopsit.lopslog.driver;

import java.util.Objects;

public class Cpf {
  private final String value;

  private Cpf(String value) {
    this.value = value;
  }

  public static Cpf of(String rawValue) {
    if (rawValue == null) throw new IllegalArgumentException("value cannot be null");
    String cleanValue = rawValue.replaceAll("\\D", "");
    if (!isValidCpf(cleanValue))
      throw new IllegalArgumentException("invalid CPF numbers or format");
    return new Cpf(cleanValue);
  }

  public String getFormatted() {
    return String.format(
        "%s.%s.%s-%s",
        value.substring(0, 3),
        value.substring(3, 6),
        value.substring(6, 9),
        value.substring(9, 11));
  }

  private static boolean isValidCpf(String cpf) {
    if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

    try {
      int sum = 0;
      for (int i = 0; i < 9; i++) {
        sum += (cpf.charAt(i) - '0') * (10 - i);
      }
      int checkDigit1 = 11 - (sum % 11);
      if (checkDigit1 >= 10) checkDigit1 = 0;

      if (checkDigit1 != (cpf.charAt(9) - '0')) return false;

      sum = 0;
      for (int i = 0; i < 10; i++) {
        sum += (cpf.charAt(i) - '0') * (11 - i);
      }
      int checkDigit2 = 11 - (sum % 11);
      if (checkDigit2 >= 10) checkDigit2 = 0;

      return checkDigit2 == (cpf.charAt(10) - '0');
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cpf cpf = (Cpf) o;
    return Objects.equals(value, cpf.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
