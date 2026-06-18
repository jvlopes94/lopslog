package com.lopsit.lopslog.driver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CpfTest {
  @Nested
  @DisplayName("Valid CPFs")
  class ValidCpfs {

    @ParameterizedTest
    @ValueSource(strings = {"096.454.360-52", "88983281090", "98886822022"})
    @DisplayName("Should accept valid CPFs")
    void shouldAcceptValidFormat(String input) {
      Cpf cpf = Cpf.of(input);
      assertNotNull(cpf);
    }
  }

  @Nested
  @DisplayName("Invalid CPFs")
  class InvalidCpfs {

    @Test
    @DisplayName("Should throw exception when value is null")
    void shouldThrowExceptionWhenNull() {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> Cpf.of(null));
      assertEquals("value cannot be null", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n"})
    @DisplayName("Should throw exception when value is empty or blank")
    void shouldThrowExceptionWhenBlank(String input) {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> Cpf.of(input));
      assertEquals("invalid CPF numbers or format", exception.getMessage());
    }

    @ParameterizedTest(name = "Input [{0}] should fail with message: {1}")
    @CsvSource(
        value = {
          "'123.456.789-01', 'invalid CPF numbers or format'",
          "' 12345678901'  , 'invalid CPF numbers or format'",
          "'12345678901 '  , 'invalid CPF numbers or format'",
          "'1234567890a'   , 'invalid CPF numbers or format'",
          "'11111111111'   , 'invalid CPF numbers or format'",
          "'123456789012'  , 'invalid CPF numbers or format'"
        })
    @DisplayName("Should throw specific exception messages for malformed formats")
    void shouldThrowExceptionForInvalidFormats(String input, String expectedMessage) {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> Cpf.of(input));

      assertEquals(expectedMessage, exception.getMessage());
    }
  }
}
