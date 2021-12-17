package com.squareshift.assignment.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Input {

  private int numberOfPassengers;
  private Integer[][] seatPlan;
}
