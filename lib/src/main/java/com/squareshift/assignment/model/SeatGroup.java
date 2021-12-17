package com.squareshift.assignment.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class SeatGroup {

  private List<Seat> seats;
  private int gridIndex;
}
