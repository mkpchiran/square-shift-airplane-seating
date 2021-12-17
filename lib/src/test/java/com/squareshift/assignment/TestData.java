package com.squareshift.assignment;

import com.squareshift.assignment.model.Location;
import com.squareshift.assignment.model.Seat;
import com.squareshift.assignment.model.SeatType;
import java.util.List;

public class TestData {

  public static Seat getMockSeatAisel() {

    return Seat.builder().location(Location.builder().build()).type(SeatType.AISEL).build();
  }

  public static Seat getMockSeatWindow() {

    return Seat.builder().location(Location.builder().build()).type(SeatType.WINDOW).build();
  }

  public static Seat getMockSeatMiddle() {

    return Seat.builder().location(Location.builder().build()).type(SeatType.MIDDLE).build();
  }

  public static List<Seat> getMockSeatList() {
    return List.of(
        getMockSeatWindow(),
        getMockSeatMiddle(),
        getMockSeatAisel(),
        getMockSeatAisel(),
        getMockSeatMiddle(),
        getMockSeatMiddle(),
        getMockSeatWindow());
  }
}
