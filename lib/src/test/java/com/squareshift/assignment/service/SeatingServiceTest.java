package com.squareshift.assignment.service;

import static com.squareshift.assignment.model.SeatType.AISEL;
import static com.squareshift.assignment.model.SeatType.MIDDLE;
import static com.squareshift.assignment.model.SeatType.WINDOW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.squareshift.assignment.helper.ValidationHelper;
import com.squareshift.assignment.helper.ValidationHelperImpl;
import com.squareshift.assignment.model.Floor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SeatingServiceTest {

  private SeatingService seatingService;
  private ValidationHelper validationHelper;

  @BeforeEach
  void init() {
    validationHelper = Mockito.mock(ValidationHelperImpl.class);
    seatingService = new SeatingServiceImpl(validationHelper);
    Mockito.clearInvocations(validationHelper);
  }

  @Test
  void test_generateFloor_singleSheet() {

    Integer[][] seatSetup = new Integer[][] {{1, 1}};
    when(validationHelper.validateSeatPlan(seatSetup)).thenReturn(true);

    Floor floor = seatingService.generateFloor(seatSetup);

    assertNotNull(floor);
    assertNotNull(floor.getSeats());
    assertEquals(1, floor.getSeats().size());
    assertEquals(WINDOW, floor.getSeats().get(0).getType());

    verify(validationHelper, times(1)).validateSeatPlan(seatSetup);
  }

  @Test
  void test_generateFloor_singleColumn() {

    Integer[][] seatSetup = new Integer[][] {{1, 3}};
    when(validationHelper.validateSeatPlan(seatSetup)).thenReturn(true);

    Floor floor = seatingService.generateFloor(seatSetup);

    assertNotNull(floor);
    assertNotNull(floor.getSeats());
    assertEquals(3, floor.getSeats().size());
    assertEquals(WINDOW, floor.getSeats().get(0).getType());
    assertEquals(WINDOW, floor.getSeats().get(1).getType());
    assertEquals(WINDOW, floor.getSeats().get(2).getType());

    verify(validationHelper, times(1)).validateSeatPlan(seatSetup);
  }

  @Test
  void test_generateFloor_multi_Column() {

    Integer[][] seatSetup = new Integer[][] {{4, 3}};
    when(validationHelper.validateSeatPlan(seatSetup)).thenReturn(true);

    Floor floor = seatingService.generateFloor(seatSetup);

    assertNotNull(floor);
    assertNotNull(floor.getSeats());
    assertEquals(12, floor.getSeats().size());
    assertEquals(WINDOW, floor.getSeats().get(0).getType());
    assertEquals(MIDDLE, floor.getSeats().get(1).getType());
    assertEquals(MIDDLE, floor.getSeats().get(2).getType());
    assertEquals(WINDOW, floor.getSeats().get(3).getType());

    verify(validationHelper, times(1)).validateSeatPlan(seatSetup);
  }

  @Test
  void test_generateFloor_multi_Column2() {

    Integer[][] seatSetup = new Integer[][] {{4, 3}, {4, 3}};
    when(validationHelper.validateSeatPlan(seatSetup)).thenReturn(true);

    Floor floor = seatingService.generateFloor(seatSetup);

    assertNotNull(floor);
    assertNotNull(floor.getSeats());
    assertEquals(24, floor.getSeats().size());
    assertEquals(WINDOW, floor.getSeats().get(0).getType());
    assertEquals(MIDDLE, floor.getSeats().get(1).getType());
    assertEquals(MIDDLE, floor.getSeats().get(2).getType());
    assertEquals(AISEL, floor.getSeats().get(3).getType());

    verify(validationHelper, times(1)).validateSeatPlan(seatSetup);
  }

  @Test
  void test_generateFloor_multi_Column3() {

    Integer[][] seatSetup = new Integer[][] {{3, 4}, {4, 5}, {2, 3}, {3, 4}};
    when(validationHelper.validateSeatPlan(seatSetup)).thenReturn(true);

    Floor floor = seatingService.generateFloor(seatSetup);

    assertNotNull(floor);
    assertNotNull(floor.getSeats());
    assertEquals(50, floor.getSeats().size());
    assertEquals(WINDOW, floor.getSeats().get(0).getType());
    assertEquals(MIDDLE, floor.getSeats().get(1).getType());
    assertEquals(AISEL, floor.getSeats().get(2).getType());
    assertEquals(WINDOW, floor.getSeats().get(3).getType());
    assertEquals(AISEL, floor.getSeats().get(8).getType());

    verify(validationHelper, times(1)).validateSeatPlan(seatSetup);
  }

  @Test
  void test_generateFloor_1() {

    Integer[][] seatSetup = new Integer[][] {{2, 3}, {1, 1}};
    when(validationHelper.validateSeatPlan(seatSetup)).thenReturn(true);

    Floor floor = seatingService.generateFloor(seatSetup);

    assertNotNull(floor);
    assertNotNull(floor.getSeats());
    assertEquals(7, floor.getSeats().size());
    assertEquals(WINDOW, floor.getSeats().get(0).getType());
    assertEquals(AISEL, floor.getSeats().get(1).getType());
    assertEquals(WINDOW, floor.getSeats().get(2).getType());
    assertEquals(AISEL, floor.getSeats().get(3).getType());
    assertEquals(WINDOW, floor.getSeats().get(4).getType());
    assertEquals(AISEL, floor.getSeats().get(5).getType());
    assertEquals(WINDOW, floor.getSeats().get(6).getType());

    verify(validationHelper, times(1)).validateSeatPlan(seatSetup);
  }

  @Test
  void test_generateFloor_2() {

    Integer[][] seatSetup = new Integer[][] {{2, 3}, {2, 3}};
    when(validationHelper.validateSeatPlan(seatSetup)).thenReturn(true);

    Floor floor = seatingService.generateFloor(seatSetup);

    assertNotNull(floor);
    assertNotNull(floor.getSeats());
    assertEquals(12, floor.getSeats().size());
    assertEquals(WINDOW, floor.getSeats().get(0).getType());
    assertEquals(AISEL, floor.getSeats().get(1).getType());
    assertEquals(WINDOW, floor.getSeats().get(2).getType());
    assertEquals(AISEL, floor.getSeats().get(3).getType());
    assertEquals(WINDOW, floor.getSeats().get(4).getType());
    assertEquals(AISEL, floor.getSeats().get(5).getType());
    assertEquals(AISEL, floor.getSeats().get(6).getType());

    verify(validationHelper, times(1)).validateSeatPlan(seatSetup);
  }
}
