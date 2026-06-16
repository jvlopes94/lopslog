package com.lopsit.lopslog.company;

import java.util.Objects;

public class Cnpj {
  private final String value;

  public Cnpj(String value) {
    validate(value);
    this.value = value;
  }

  private void validate(String value) {
    if (value == null) throw new IllegalArgumentException("value cannot be null");
    if (value.isBlank()) throw new IllegalArgumentException("value cannot be blank");
    if (value.length() != 14) throw new IllegalArgumentException("value must have 14 characters");
    if (!value.matches("[0-9]{14}")) throw new IllegalArgumentException("value must be numeric");
    if (value.matches("(\\d)\\1{13}"))
      throw new IllegalArgumentException("value cannot have repeated digits");

    int sum = 0;
    int[] weight1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    for (int i = 0; i < 12; i++) {
      sum += Character.getNumericValue(value.charAt(i)) * weight1[i];
    }

    int digit1 = 11 - (sum % 11);
    if (digit1 == 10 || digit1 == 11) digit1 = 0;

    sum = 0;
    int[] weight2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    for (int i = 0; i < 13; i++) {
      sum += Character.getNumericValue(value.charAt(i)) * weight2[i];
    }

    int digit2 = 11 - (sum % 11);
    if (digit2 == 10 || digit2 == 11) digit2 = 0;

    boolean correctDigit1 = digit1 == Character.getNumericValue(value.charAt(12));
    boolean correctDigit2 = digit2 == Character.getNumericValue(value.charAt(13));
    if (!correctDigit1 || !correctDigit2) throw new IllegalArgumentException("value is invalid");
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;

    Cnpj cnpj = (Cnpj) o;
    return Objects.equals(value, cnpj.value);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }
}
