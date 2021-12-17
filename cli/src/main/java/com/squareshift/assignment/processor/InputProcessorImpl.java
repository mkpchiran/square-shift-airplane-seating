package com.squareshift.assignment.processor;

import com.google.inject.Inject;
import com.squareshift.assignment.exception.InvalidNumberOfPassengersException;
import com.squareshift.assignment.model.Input;
import com.squareshift.assignment.utils.SeatPlanUtils;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class InputProcessorImpl implements InputProcessor {

  private final SeatPlanUtils seatPlanUtils;

  @Inject
  public InputProcessorImpl(SeatPlanUtils seatPlanUtils) {
    this.seatPlanUtils = seatPlanUtils;
  }

  @Override
  public Input processInput(String seatPlan, String numberOfPassengers) {

    List<List<Integer>> generateSeatPlan = this.seatPlanUtils.generateSeatPlan(seatPlan);
    Integer[][] seatPlanArray = this.seatPlanUtils.convertSeatPlan(generateSeatPlan);

    try {
      return Input.builder()
          .seatPlan(seatPlanArray)
          .numberOfPassengers(Integer.parseInt(numberOfPassengers))
          .build();
    } catch (Exception e) {
      log.error(e);
      throw new InvalidNumberOfPassengersException(
          "Invalid input for Passenger " + numberOfPassengers);
    }
  }
}
