package com.squareshift.assignment.processor;

import static com.squareshift.assignment.TestData.getMockSeatList;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.squareshift.assignment.model.Allocation;
import com.squareshift.assignment.model.Seat;
import com.squareshift.assignment.model.SeatGroup;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutputProcessorTest {

  private OutputProcessor outputProcessor;

  @BeforeEach
  void init() {
    outputProcessor = new OutputProcessorImpl();
  }

  @Test
  void test_generatePlan_valid() {

    AtomicInteger i = new AtomicInteger();
    List<Seat> seats = getMockSeatList();
    seats.forEach(e -> e.setPassengerIndex(i.getAndIncrement()));
    List<SeatGroup> seatGroups = List.of(SeatGroup.builder().seats(seats).build());
    Allocation allocation =
        Allocation.builder().seats(seats).seatGroups(seatGroups).pending(0).build();

    XSSFWorkbook workbook = outputProcessor.generatePlan(allocation);

    assertNotNull(workbook);
    XSSFSheet sheet = workbook.getSheetAt(0);
    assertNotNull(sheet);
  }

  @Test
  void test_generatePlan_pending() {

    AtomicInteger i = new AtomicInteger();
    List<Seat> seats = getMockSeatList();
    seats.forEach(e -> e.setPassengerIndex(i.getAndIncrement()));
    List<SeatGroup> seatGroups = List.of(SeatGroup.builder().seats(seats).build());
    Allocation allocation =
        Allocation.builder().seats(seats).seatGroups(seatGroups).pending(10).build();

    XSSFWorkbook workbook = outputProcessor.generatePlan(allocation);

    assertNotNull(workbook);
    XSSFSheet sheet = workbook.getSheetAt(0);
    assertNotNull(sheet);
  }
}
