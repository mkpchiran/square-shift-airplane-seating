package com.squareshift.assignment.helper;

import com.squareshift.assignment.exception.InvalidNumberOfPassengersException;
import com.squareshift.assignment.exception.InvalidSeatInputException;
import java.util.Arrays;

/** Validation Helper to validate inputs */
public class ValidationHelperImpl implements ValidationHelper {

  /**
   * @param passengers number of passengers
   * @return validation status
   */
  public boolean validatePassengers(int passengers) {

    if (passengers < 0) {
      throw new InvalidNumberOfPassengersException(
          "Number of Passengers should be Positive. Wrong Input " + passengers);
    }
    return true;
  }

  /**
   * @param seatPlan 2d array of seat plan
   * @return validation status
   */
  @Override
  public boolean validateSeatPlan(Integer[][] seatPlan) {
    for (Integer[] s : seatPlan) {
      if (s.length != 2 || Arrays.stream(s).anyMatch(e -> e < 0)) {
        throw new InvalidSeatInputException(
            "Invalid Seat plan for input " + Arrays.deepToString(seatPlan));
      }
    }
    return true;
  }
}
