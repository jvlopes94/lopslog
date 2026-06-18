package com.lopsit.lopslog.driver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CnhTest {
  @Nested
  @DisplayName("Valid CNHs")
  class ValidCnhs {

    @ParameterizedTest
    @ValueSource(strings = {"51364981501", "69154989087", "21746380888"})
    @DisplayName("Should accept valid CNHs")
    void shouldAcceptValidFormat(String input) {
      Cnh cnh = Cnh.of(input);
      assertNotNull(cnh);
    }
  }

  @Nested
  @DisplayName("Invalid CNHs")
  class InvalidCnhs {

    @Test
    @DisplayName("Should throw exception when value is null")
    void shouldThrowExceptionWhenNull() {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> Cnh.of(null));
      assertEquals("value cannot be null", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n"})
    @DisplayName("Should throw exception when value is empty or blank")
    void shouldThrowExceptionWhenBlank(String input) {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> Cnh.of(input));
      assertEquals("invalid CNH numbers or format", exception.getMessage());
    }

    @ParameterizedTest(name = "Input [{0}] should fail with message: {1}")
    @CsvSource(
        value = {
          "'123456789-01', 'invalid CNH numbers or format'",
          "' 12345678901' , 'invalid CNH numbers or format'",
          "'12345678901 ' , 'invalid CNH numbers or format'",
          "'1234567890a'  , 'invalid CNH numbers or format'",
          "'11111111111'  , 'invalid CNH numbers or format'",
          "'123456789012' , 'invalid CNH numbers or format'",
          "'12345678909'  , 'invalid CNH numbers or format'"
        })
    @DisplayName("Should throw specific exception messages for malformed formats")
    void shouldThrowExceptionForInvalidFormats(String input, String expectedMessage) {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> Cnh.of(input));

      assertEquals(expectedMessage, exception.getMessage());
    }
  }
}
