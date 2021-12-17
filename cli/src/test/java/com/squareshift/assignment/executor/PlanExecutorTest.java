package com.squareshift.assignment.executor;

import static com.squareshift.assignment.TestData.getMockInput;
import static com.squareshift.assignment.constants.StringConstants.EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.squareshift.assignment.exception.InvalidSeatInputException;
import com.squareshift.assignment.model.Allocation;
import com.squareshift.assignment.model.Floor;
import com.squareshift.assignment.model.Input;
import com.squareshift.assignment.processor.InputProcessor;
import com.squareshift.assignment.processor.InputProcessorImpl;
import com.squareshift.assignment.processor.OutputProcessor;
import com.squareshift.assignment.processor.OutputProcessorImpl;
import com.squareshift.assignment.service.AllocationService;
import com.squareshift.assignment.service.AllocationServiceImpl;
import com.squareshift.assignment.service.SeatingService;
import com.squareshift.assignment.service.SeatingServiceImpl;
import com.squareshift.assignment.utils.ScannerUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PlanExecutorTest {

  private InputProcessor inputProcessor;
  private SeatingService seatingService;
  private AllocationService allocationService;
  private OutputProcessor outputProcessor;
  private ScannerUtils scannerUtils;
  private File file;
  private PrintStream outputStream;
  private PlanExecutor planExecutor;

  @BeforeEach
  void init() throws IOException {
    inputProcessor = Mockito.mock(InputProcessorImpl.class);
    seatingService = Mockito.mock(SeatingServiceImpl.class);
    allocationService = Mockito.mock(AllocationServiceImpl.class);
    outputProcessor = Mockito.mock(OutputProcessorImpl.class);
    file = File.createTempFile("test", ".xlsx");
    outputStream = Mockito.mock(PrintStream.class);
    scannerUtils = Mockito.mock(ScannerUtils.class);
    planExecutor =
        new PlanExecutorImpl(
            inputProcessor, seatingService, allocationService, outputProcessor, scannerUtils);
    Mockito.clearInvocations(
        inputProcessor,
        seatingService,
        allocationService,
        outputProcessor,
        outputStream,
        scannerUtils);
  }

  @Test
  void test_execute_valid() throws IOException {

    Input input = getMockInput();
    input.setNumberOfPassengers(9);
    Floor floor = Mockito.mock(Floor.class);
    Allocation allocation = Mockito.mock(Allocation.class);
    XSSFWorkbook workbook = new XSSFWorkbook();

    String sheetPlan = "[[2,3][2,3]";
    String passengerCount = "9";

    Mockito.when(inputProcessor.processInput(sheetPlan, passengerCount)).thenReturn(input);
    Mockito.when(seatingService.generateFloor(input.getSeatPlan())).thenReturn(floor);
    Mockito.when(allocationService.allocate(floor, input.getNumberOfPassengers()))
        .thenReturn(allocation);
    Mockito.when(outputProcessor.generatePlan(allocation)).thenReturn(workbook);
    Mockito.when(scannerUtils.nextLine()).thenReturn(sheetPlan, passengerCount);
    String outPutFile = planExecutor.execute(file, outputStream);

    assertTrue(file.exists());
    assertEquals(file.getPath(), outPutFile);

    verify(inputProcessor, times(1)).processInput(sheetPlan, passengerCount);
    verify(seatingService, times(1)).generateFloor(input.getSeatPlan());
    verify(allocationService, times(1)).allocate(floor, input.getNumberOfPassengers());
    verify(outputProcessor, times(1)).generatePlan(allocation);
    verify(scannerUtils, times(2)).nextLine();
  }

  @Test
  void test_execute_invalid() throws IOException {

    Input input = getMockInput();
    input.setNumberOfPassengers(9);

    Mockito.when(scannerUtils.nextLine()).thenReturn(EMPTY);
    Mockito.when(inputProcessor.processInput(anyString(), anyString()))
        .thenThrow(new InvalidSeatInputException("Test Exception"));
    String s = planExecutor.execute(file, outputStream);

    assertNotNull(s);
    assertEquals("", s);

    verify(inputProcessor, times(1)).processInput(anyString(), anyString());
  }
}
