package com.squareshift.assignment.service;

import com.squareshift.assignment.model.Floor;

/** Seating Service create the floor plans with seats, seat groups and do the categorizing */
public interface SeatingService {

  /**
   * @param seatSetup 2D integer array for input of seat plan. ex [[3,4],[4,5],[2,3],[3,4]]
   * @return {@link Floor}
   */
  Floor generateFloor(Integer[][] seatSetup);
}
