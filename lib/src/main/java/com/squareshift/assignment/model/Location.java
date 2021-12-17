package com.squareshift.assignment.model;

import com.google.common.collect.ComparisonChain;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location implements Comparable<Location> {

  private int rowIndex;
  private int columnIndex;
  private int gridIndex;

  @Override
  public int compareTo(Location o) {
    return ComparisonChain.start()
        .compare(rowIndex, o.getRowIndex())
        .compare(gridIndex, o.getGridIndex())
        .result();
  }
}
