package com.lopsit.lopslog.vehicle;

import java.util.regex.Pattern;

public class LicensePlate {
  private static final Pattern LEGACY = Pattern.compile("^[A-Z]{3}-?\\d{4}$");
  private static final Pattern MERCOSUL = Pattern.compile("^[A-Z]{3}-?\\d[A-Z]\\d{2}$");

  private final String value;

  public LicensePlate(String value) {
    if (value == null) {
      throw new IllegalArgumentException("LicensePlate value cannot be null");
    }

    String normalized = value.trim().toUpperCase();

    if (normalized.isEmpty()) {
      throw new IllegalArgumentException("LicensePlate value cannot be blank");
    }

    if (!LEGACY.matcher(normalized).matches() && !MERCOSUL.matcher(normalized).matches()) {
      throw new IllegalArgumentException("Invalid LicensePlate value");
    }

    this.value = normalized.replace("-", "");
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;

    LicensePlate that = (LicensePlate) o;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
