package com.lopsit.lopslog.driver;

import java.util.Objects;

public class Cnh {
  private final String value;

  private Cnh(String value) {
    this.value = value;
  }

  public static Cnh of(String rawValue) {
    if (rawValue == null) throw new IllegalArgumentException("value cannot be null");
    String cleanValue = rawValue.replaceAll("\\D", "");
    if (!isValidCnh(cleanValue))
      throw new IllegalArgumentException("invalid CNH numbers or format");
    return new Cnh(cleanValue);
  }

  private static boolean isValidCnh(String cnh) {
    if (cnh.length() != 11 || cnh.matches("(\\d)\\1{10}")) return false;

    try {
      // First verification digit calculation
      int sum = 0;
      for (int i = 0, weight = 9; i < 9; i++, weight--) {
        sum += (cnh.charAt(i) - '0') * weight;
      }

      int dv1 = sum % 11;
      int factor = 0;
      if (dv1 >= 10) {
        dv1 = 0;
        factor = 2;
      }

      if (dv1 != (cnh.charAt(9) - '0')) return false;

      // Second verification digit calculation
      sum = 0;
      for (int i = 0, weight = 1; i < 9; i++, weight++) {
        sum += (cnh.charAt(i) - '0') * weight;
      }

      int dv2 = sum % 11;
      if (dv2 >= 10) {
        dv2 = 0;
      } else {
        dv2 = dv2 - factor;
        if (dv2 < 0) dv2 += 11;
      }

      return dv2 == (cnh.charAt(10) - '0');
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cnh cnh = (Cnh) o;
    return Objects.equals(value, cnh.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
