package com.lopsit.lopslog.vehicle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LicensePlateTest {
  @Nested
  @DisplayName("Valid License Plates")
  class ValidPlates {

    @ParameterizedTest
    @ValueSource(strings = {"ABC-1234", "abc-1234", "  ABC-1234  ", "ABC1234"})
    @DisplayName("Should accept valid Legacy format variants and normalize them")
    void shouldAcceptValidLegacyFormat(String input) {
      LicensePlate plate = new LicensePlate(input);
      assertNotNull(plate);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABC1D23", "abc-1d23", "  ABC-1D23  ", "ABC1d23"})
    @DisplayName("Should accept valid Mercosul format variants and normalize them")
    void shouldAcceptValidMercosulFormat(String input) {
      LicensePlate plate = new LicensePlate(input);
      assertNotNull(plate);
    }

    @Test
    @DisplayName("Should remove hyphen and normalize formatting internally")
    void shouldNormalizeInternally() {
      LicensePlate plateWithHyphen = new LicensePlate("ABC-1234");
      LicensePlate plateWithoutHyphen = new LicensePlate("abc1234");

      // Because they normalize to the same internal value, they should be equal
      assertEquals(plateWithHyphen, plateWithoutHyphen);
    }
  }

  @Nested
  @DisplayName("Invalid License Plates")
  class InvalidPlates {

    @Test
    @DisplayName("Should throw exception when value is null")
    void shouldThrowExceptionWhenNull() {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> new LicensePlate(null));
      assertEquals("LicensePlate value cannot be null", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n"})
    @DisplayName("Should throw exception when value is empty or blank")
    void shouldThrowExceptionWhenBlank(String input) {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> new LicensePlate(input));
      assertEquals("LicensePlate value cannot be blank", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
          "AB-1234", // Too few letters (Legacy)
          "ABCD-123", // Too many letters (Legacy)
          "ABC-123", // Too few numbers (Legacy)
          "ABC-12345", // Too many numbers (Legacy)
          "ABC123A", // Invalid Mercosul (letter in wrong position)
          "123-ABCD", // Inverted order
          "ABC-12D34" // Too many characters
        })
    @DisplayName("Should throw exception for malformed formats")
    void shouldThrowExceptionForInvalidFormats(String input) {
      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> new LicensePlate(input));
      assertEquals("Invalid LicensePlate value", exception.getMessage());
    }
  }

  @Nested
  @DisplayName("Equals and HashCode Contracts")
  class EqualsAndHashCode {

    @Test
    @DisplayName("Should be equal to itself and another plate with identical normalized values")
    void testEqualsContract() {
      LicensePlate plate1 = new LicensePlate("ABC-1234");
      LicensePlate plate2 = new LicensePlate("abc1234");
      LicensePlate plate3 = new LicensePlate("XYZ-9999");

      assertNotNull(plate1);
      assertEquals(plate1, plate2);
      assertNotEquals(plate1, plate3);
    }

    @Test
    @DisplayName("Should produce identical hashcodes for structurally equal plates")
    void testHashCodeContract() {
      LicensePlate plate1 = new LicensePlate("ABC-1234");
      LicensePlate plate2 = new LicensePlate("abc1234");

      assertEquals(plate1.hashCode(), plate2.hashCode());
    }
  }
}
