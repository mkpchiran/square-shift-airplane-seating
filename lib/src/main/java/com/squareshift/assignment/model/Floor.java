package com.squareshift.assignment.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Floor {

  private List<Seat> seats;
}
