package com.squareshift.assignment.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.squareshift.assignment.exception.InvalidNumberOfPassengersException;
import com.squareshift.assignment.model.Input;
import com.squareshift.assignment.utils.SeatPlanUtils;
import com.squareshift.assignment.utils.SeatPlanUtilsImpl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class InputProcessorTest {

  private InputProcessor inputProcessor;
  private SeatPlanUtils seatPlanUtils;

  @BeforeEach
  void init() {
    seatPlanUtils = Mockito.mock(SeatPlanUtilsImpl.class);
    inputProcessor = new InputProcessorImpl(seatPlanUtils);
  }

  @Test
  void test_process_input_valid() {
    String seatPlan = "";
    String numberOfPassengers = "2";
    List<List<Integer>> lists = List.of(List.of(2, 3), List.of(3, 2));
    Integer[][] integers = new Integer[][] {{2, 3}, {3, 2}};
    when(seatPlanUtils.generateSeatPlan(seatPlan)).thenReturn(lists);
    when(seatPlanUtils.convertSeatPlan(lists)).thenReturn(integers);
    Input input = inputProcessor.processInput(seatPlan, numberOfPassengers);

    assertNotNull(input);
    assertEquals(2, input.getNumberOfPassengers());

    verify(seatPlanUtils, times(1)).convertSeatPlan(lists);
    verify(seatPlanUtils, times(1)).generateSeatPlan(seatPlan);
  }

  @Test
  void test_process_input_invalid() {
    String seatPlan = "";
    String numberOfPassengers = "lo";
    List<List<Integer>> lists = List.of(List.of(2, 3), List.of(3, 2));
    Integer[][] integers = new Integer[][] {{2, 3}, {3, 2}};
    when(seatPlanUtils.generateSeatPlan(seatPlan)).thenReturn(lists);
    when(seatPlanUtils.convertSeatPlan(lists)).thenReturn(integers);
    InvalidNumberOfPassengersException exception =
        assertThrows(
            InvalidNumberOfPassengersException.class,
            () -> inputProcessor.processInput(seatPlan, numberOfPassengers));

    assertNotNull(exception);
    assertEquals("Invalid input for Passenger lo", exception.getMessage());

    verify(seatPlanUtils, times(1)).convertSeatPlan(lists);
    verify(seatPlanUtils, times(1)).generateSeatPlan(seatPlan);
  }
}
