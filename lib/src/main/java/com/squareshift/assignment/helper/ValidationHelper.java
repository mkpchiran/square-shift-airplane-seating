package com.squareshift.assignment.helper;

/** Validation Helper to validate inputs */
public interface ValidationHelper {

  /**
   * @param passengers number of passengers
   * @return validation status
   */
  boolean validatePassengers(int passengers);

  /**
   * @param seatPlan 2d array of seat plan
   * @return validation status
   */
  boolean validateSeatPlan(Integer[][] seatPlan);
}
