package com.squareshift.assignment.processor;

import com.squareshift.assignment.model.Input;

public interface InputProcessor {

  Input processInput(String seatPlan, String numberOfPassengers);
}
