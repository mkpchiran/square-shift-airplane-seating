package com.squareshift.assignment.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.squareshift.assignment.exception.InvalidNumberOfPassengersException;
import com.squareshift.assignment.exception.InvalidSeatInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidationHelperTest {

  private ValidationHelper validationHelper;

  @BeforeEach
  void init() {
    validationHelper = new ValidationHelperImpl();
  }

  @Test
  void test_validatePassengers_positive_integers() {
    boolean result = validationHelper.validatePassengers(1);
    assertTrue(result);
  }

  @Test
  void test_validatePassengers_negative_integers() {
    InvalidNumberOfPassengersException exception =
        assertThrows(
            InvalidNumberOfPassengersException.class,
            () -> validationHelper.validatePassengers(-1));
    assertEquals("Number of Passengers should be Positive. Wrong Input -1", exception.getMessage());
  }

  @Test
  void test_validatePassengers_zero() {
    boolean result = validationHelper.validatePassengers(0);
    assertTrue(result);
  }

  @Test
  void test_validateSeatPlane_empty_array() {

    InvalidSeatInputException exception =
        assertThrows(
            InvalidSeatInputException.class,
            () -> validationHelper.validateSeatPlan(new Integer[][] {{}}));
    assertEquals("Invalid Seat plan for input [[]]", exception.getMessage());
  }

  @Test
  void test_validateSeatPlane_singleItem_2d_array() {

    InvalidSeatInputException exception =
        assertThrows(
            InvalidSeatInputException.class,
            () -> validationHelper.validateSeatPlan(new Integer[][] {{1}}));
    assertEquals("Invalid Seat plan for input [[1]]", exception.getMessage());
  }

  @Test
  void test_validateSeatPlane_threeItem_2d_array() {

    InvalidSeatInputException exception =
        assertThrows(
            InvalidSeatInputException.class,
            () -> validationHelper.validateSeatPlan(new Integer[][] {{1, 2, 3}}));
    assertEquals("Invalid Seat plan for input [[1, 2, 3]]", exception.getMessage());
  }

  @Test
  void test_validateSeatPlane_negativeItem_2d_array() {

    InvalidSeatInputException exception =
        assertThrows(
            InvalidSeatInputException.class,
            () -> validationHelper.validateSeatPlan(new Integer[][] {{1, -2}}));
    assertEquals("Invalid Seat plan for input [[1, -2]]", exception.getMessage());
  }

  @Test
  void test_validateSeatPlane_twoItem_2d_array() {

    boolean result =
        validationHelper.validateSeatPlan(
            new Integer[][] {
              {
                1, 2,
              }
            });
    assertTrue(result);
  }

  @Test
  void test_validateSeatPlane_multiItem_2d_array() {

    boolean result =
        validationHelper.validateSeatPlan(
            new Integer[][] {
              {
                1, 2,
              },
              {0, 0}
            });
    assertTrue(result);
  }
}
