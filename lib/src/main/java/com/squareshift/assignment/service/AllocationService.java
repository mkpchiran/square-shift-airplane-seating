package com.squareshift.assignment.service;

import com.squareshift.assignment.model.Allocation;
import com.squareshift.assignment.model.Floor;

/***
 * Allocation Service allocates passengers for seating plan.
 */
public interface AllocationService {

  /**
   * @param floor {@link Floor}
   * @param passengerCount passenger count to allocate
   * @return {@link Allocation}
   */
  Allocation allocate(Floor floor, int passengerCount);
}
