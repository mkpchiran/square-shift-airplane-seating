package com.squareshift.assignment.service;

import static java.util.stream.Collectors.groupingBy;

import com.google.inject.Inject;
import com.squareshift.assignment.helper.ValidationHelper;
import com.squareshift.assignment.model.Allocation;
import com.squareshift.assignment.model.Floor;
import com.squareshift.assignment.model.Seat;
import com.squareshift.assignment.model.SeatGroup;
import com.squareshift.assignment.model.SeatType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Allocation Service allocates passengers for seating plan.
 */
public class AllocationServiceImpl implements AllocationService {

  private final ValidationHelper validationHelper;

  @Inject
  public AllocationServiceImpl(ValidationHelper validationHelper) {
    this.validationHelper = validationHelper;
  }

  /**
   * @param floor {@link Floor}
   * @param passengerCount passenger count to allocate
   * @return {@link Allocation}
   */
  @Override
  public Allocation allocate(Floor floor, int passengerCount) {

    this.validationHelper.validatePassengers(passengerCount);

    List<Seat> seats = new ArrayList<>();

    seats.addAll(
        floor.getSeats().stream()
            .filter(e -> e.getType().equals(SeatType.AISEL))
            .sorted()
            .collect(Collectors.toList()));
    seats.addAll(
        floor.getSeats().stream()
            .filter(e -> e.getType().equals(SeatType.WINDOW))
            .sorted()
            .collect(Collectors.toList()));
    seats.addAll(
        floor.getSeats().stream()
            .filter(e -> e.getType().equals(SeatType.MIDDLE))
            .sorted()
            .collect(Collectors.toList()));

    for (int i = 1; (i <= passengerCount) && (i <= seats.size()); i++) {

      floor.getSeats().get(floor.getSeats().indexOf(seats.get(i - 1))).setPassengerIndex(i);
    }

    return Allocation.builder()
        .seats(floor.getSeats())
        .seatGroups(
            floor.getSeats().stream()
                .collect(groupingBy(seat -> seat.getLocation().getGridIndex()))
                .entrySet()
                .stream()
                .map(k -> SeatGroup.builder().gridIndex(k.getKey()).seats(k.getValue()).build())
                .collect(Collectors.toList()))
        .pending(Math.max(0, passengerCount - seats.size()))
        .build();
  }
}
