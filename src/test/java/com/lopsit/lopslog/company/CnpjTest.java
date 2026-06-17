package com.lopsit.lopslog.company;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CnpjTest {

  @Nested
  @DisplayName("Valid CNPJs")
  class ValidCnpjs {

    @ParameterizedTest
    @ValueSource(strings = {"45174649000167", "98599267000141", "36613826000173"})
    @DisplayName("Should accept valid CNPJs")
    void shouldAcceptValidFormat(String input) {
      Cnpj cnpj = new Cnpj(input);
      assertNotNull(cnpj);
    }
  }

  @Nested
  @DisplayName("Invalid CNPJs")
  class InvalidCnpjs {

    @Test
    @DisplayName("Should throw exception when value is null")
    void shouldThrowExceptionWhenNull() {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> new Cnpj(null));
      assertEquals("value cannot be null", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n"})
    @DisplayName("Should throw exception when value is empty or blank")
    void shouldThrowExceptionWhenBlank(String input) {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> new Cnpj(input));
      assertEquals("value cannot be blank", exception.getMessage());
    }

    @ParameterizedTest(name = "Input [{0}] should fail with message: {1}")
    @CsvSource(
        value = {
          "'63.855.557/0001-24', 'value must have 14 characters'",
          "' 09095250000127'   , 'value must have 14 characters'",
          "'33800459000129 '   , 'value must have 14 characters'",
          "'15.388.299 0001-70', 'value must have 14 characters'",
          "'031483f0000123'    , 'value must be numeric'",
          "'22222222222222'    , 'value cannot have repeated digits'",
          "'897229200001274'   , 'value must have 14 characters'"
        })
    @DisplayName("Should throw specific exception messages for malformed formats")
    void shouldThrowExceptionForInvalidFormats(String input, String expectedMessage) {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> new Cnpj(input));

      assertEquals(expectedMessage, exception.getMessage());
    }
  }
}
