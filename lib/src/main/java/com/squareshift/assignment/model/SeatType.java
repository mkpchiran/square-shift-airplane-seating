package com.squareshift.assignment.model;

/** Hold the sheet type of the floor. */
public enum SeatType {
  /** Sheets in the start and the end of a sheet group and not in window side. */
  AISEL(0),
  /** Sheets in the start and the end of a sheet group and in window side. */
  WINDOW(1),
  /** Sheets in the middle of a sheet group. */
  MIDDLE(2);

  SeatType(int i) {}
}
