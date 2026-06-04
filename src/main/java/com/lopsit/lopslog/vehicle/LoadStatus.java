package com.lopsit.lopslog.vehicle;

public enum LoadStatus {
  LOADED("LOADED", "C"),
  EMPTY("EMPTY", "V"),
  LOADED_THEN_EMPTY("LOADED_THEN_EMPTY", "C/V"),
  EMPTY_THEN_LOADED("EMPTY_THEN_LOADED", "V/C");

  LoadStatus(String value, String label) {}
}
