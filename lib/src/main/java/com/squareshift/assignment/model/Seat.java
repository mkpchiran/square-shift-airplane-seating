package com.squareshift.assignment.model;

import com.google.common.collect.ComparisonChain;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Seat implements Comparable<Seat> {

  private Location location;
  private SeatType type;
  private int passengerIndex;

  @Override
  public int compareTo(Seat o) {
    return ComparisonChain.start()
        .compare(this.getLocation(), o.getLocation())
        .compare(this.getType(), o.getType())
        .result();
  }
}
