package com.squareshift.assignment.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.squareshift.assignment.exception.InvalidSeatInputException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SeatPlanUtilsTest {

  private SeatPlanUtils seatPlanUtils;

  @BeforeEach
  void init() {
    seatPlanUtils = new SeatPlanUtilsImpl();
  }

  @Test
  void test_generateSeatPlan_valid() {

    String seatPlan = "[[1,3],[3,3]]";

    List<List<Integer>> lists = seatPlanUtils.generateSeatPlan(seatPlan);

    assertNotNull(lists);
    assertEquals(2, lists.size());
    assertEquals(2, lists.get(0).size());
    assertEquals(2, lists.get(1).size());
  }

  @Test
  void test_generateSeatPlan_badInput() {

    String seatPlan = "[[1,3][3,3]]";

    InvalidSeatInputException exception =
        assertThrows(
            InvalidSeatInputException.class, () -> seatPlanUtils.generateSeatPlan(seatPlan));

    assertNotNull(exception);
    assertEquals("Seat Plan input is invalid for  [[1,3][3,3]]", exception.getMessage());
  }

  @Test
  void test_generateSeatPlan_empty() {

    String seatPlan = "";

    InvalidSeatInputException exception =
        assertThrows(
            InvalidSeatInputException.class, () -> seatPlanUtils.generateSeatPlan(seatPlan));

    assertNotNull(exception);
    assertEquals("Seat Plan input is invalid for  ", exception.getMessage());
  }

  @Test
  void test_generateSeatPlan_null() {

    String seatPlan = null;

    InvalidSeatInputException exception =
        assertThrows(
            InvalidSeatInputException.class, () -> seatPlanUtils.generateSeatPlan(seatPlan));

    assertNotNull(exception);
    assertEquals("Seat Plan input is invalid for  null", exception.getMessage());
  }

  @Test
  void test_convertSeatPlan_valid() {

    List<List<Integer>> seatPlan = List.of(List.of(2, 3), List.of(3, 2));

    Integer[][] integers = seatPlanUtils.convertSeatPlan(seatPlan);

    assertNotNull(integers);
    assertEquals(2, integers.length);
    assertEquals(2, integers[0].length);
    assertEquals(2, integers[0].length);
  }
}
