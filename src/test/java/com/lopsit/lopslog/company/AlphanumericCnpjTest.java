package com.lopsit.lopslog.company;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class AlphanumericCnpjTest {

  @Nested
  @DisplayName("Valid Alphanumeric CNPJs")
  class ValidAlphanumericCnpjs {

//    @ParameterizedTest
    @ValueSource(strings = {"", "", ""}) // TODO
    @DisplayName("Should accept valid CNPJs")
    void shouldAcceptValidFormat(String input) {
      AlphanumericCnpj cnpj = new AlphanumericCnpj(input);
      assertNotNull(cnpj);
    }
  }

  @Nested
  @DisplayName("Invalid Alphanumeric CNPJs")
  class InvalidAlphanumericCnpjs {

    @Test
    @DisplayName("Should throw exception when value is null")
    void shouldThrowExceptionWhenNull() {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> new AlphanumericCnpj(null));
      assertEquals("value cannot be null", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n"})
    @DisplayName("Should throw exception when value is empty or blank")
    void shouldThrowExceptionWhenBlank(String input) {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> new AlphanumericCnpj(input));
      assertEquals("value cannot be blank", exception.getMessage());
    }

//    @ParameterizedTest(name = "Input [{0}] should fail with message: {1}")
    @CsvSource(
        value = {
          "'', ''", // TODO
        })
    @DisplayName("Should throw specific exception messages for malformed formats")
    void shouldThrowExceptionForInvalidFormats(String input, String expectedMessage) {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> new AlphanumericCnpj(input));

      assertEquals(expectedMessage, exception.getMessage());
    }
  }
}
