package com.squareshift.assignment.service;

import com.google.inject.Inject;
import com.squareshift.assignment.helper.ValidationHelper;
import com.squareshift.assignment.model.Floor;
import com.squareshift.assignment.model.Location;
import com.squareshift.assignment.model.Seat;
import com.squareshift.assignment.model.SeatType;
import java.util.ArrayList;
import java.util.List;

/** Seating Service create the floor plans with seats, seat groups and do the categorizing */
public class SeatingServiceImpl implements SeatingService {

  private final ValidationHelper validationHelper;

  @Inject
  public SeatingServiceImpl(ValidationHelper validationHelper) {
    this.validationHelper = validationHelper;
  }

  /**
   * @param seatSetup 2D integer array for input of seat plan. ex [[3,4],[4,5],[2,3],[3,4]]
   * @return {@link Floor}
   */
  @Override
  public Floor generateFloor(Integer[][] seatSetup) {
    this.validationHelper.validateSeatPlan(seatSetup);
    List<Seat> seats = new ArrayList<>();
    for (int gridIndex = 0; gridIndex < seatSetup.length; gridIndex++) {
      Integer[] grid = seatSetup[gridIndex];
      int numRows = grid[1];
      int numColumns = grid[0];
      for (int row = 0; row < numRows; row++) {
        for (int column = 0; column < numColumns; column++) {

          seats.add(
              Seat.builder()
                  .location(
                      Location.builder()
                          .rowIndex(row)
                          .columnIndex(column)
                          .gridIndex(gridIndex)
                          .build())
                  .type(getSeatType(column, numColumns, gridIndex, seatSetup.length))
                  .build());
        }
      }
    }
    return Floor.builder().seats(seats).build();
  }

  private SeatType getSeatType(int column, int numColumns, int gridIndex, int setupLength) {

    if ((gridIndex == 0 && column == 0)
        || (gridIndex == setupLength - 1 && column == numColumns - 1)) {
      return SeatType.WINDOW;
    } else if ((column == numColumns - 1 && gridIndex != setupLength - 1) || (column == 0)) {
      return SeatType.AISEL;
    } else {
      return SeatType.MIDDLE;
    }
  }
}
