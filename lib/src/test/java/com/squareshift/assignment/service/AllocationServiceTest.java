package com.squareshift.assignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.squareshift.assignment.TestData;
import com.squareshift.assignment.helper.ValidationHelper;
import com.squareshift.assignment.helper.ValidationHelperImpl;
import com.squareshift.assignment.model.Allocation;
import com.squareshift.assignment.model.Floor;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AllocationServiceTest {

  private AllocationService allocationService;
  private ValidationHelper validationHelper;

  @BeforeEach
  void init() {
    validationHelper = Mockito.mock(ValidationHelperImpl.class);
    allocationService = new AllocationServiceImpl(validationHelper);
    Mockito.clearInvocations(validationHelper);
  }

  @Test
  void test_allocate_1() {
    int passengerCount = 3;
    when(validationHelper.validatePassengers(passengerCount)).thenReturn(true);

    Allocation allocation =
        allocationService.allocate(
            Floor.builder().seats(TestData.getMockSeatList()).build(), passengerCount);

    assertNotNull(allocation);
    assertEquals(0, allocation.getPending());

    verify(validationHelper, times(1)).validatePassengers(passengerCount);
  }

  @Test
  void test_allocate_pending_passengers() {
    int passengerCount = 300;
    when(validationHelper.validatePassengers(passengerCount)).thenReturn(true);

    Allocation allocation =
        allocationService.allocate(
            Floor.builder().seats(TestData.getMockSeatList()).build(), passengerCount);

    assertNotNull(allocation);
    assertEquals(293, allocation.getPending());
    verify(validationHelper, times(1)).validatePassengers(passengerCount);
  }

  @Test
  void test_allocate_zero_passengers() {
    int passengerCount = 0;
    when(validationHelper.validatePassengers(passengerCount)).thenReturn(true);

    Allocation allocation =
        allocationService.allocate(
            Floor.builder().seats(TestData.getMockSeatList()).build(), passengerCount);

    assertNotNull(allocation);
    assertEquals(0, allocation.getPending());
    verify(validationHelper, times(1)).validatePassengers(passengerCount);
  }

  @Test
  void test_allocate_zero_sheets() {
    int passengerCount = 0;
    when(validationHelper.validatePassengers(passengerCount)).thenReturn(true);

    Allocation allocation =
        allocationService.allocate(
            Floor.builder().seats(new ArrayList<>()).build(), passengerCount);

    assertNotNull(allocation);
    assertEquals(0, allocation.getPending());
    assertEquals(0, allocation.getSeats().size());
    verify(validationHelper, times(1)).validatePassengers(passengerCount);
  }
}
