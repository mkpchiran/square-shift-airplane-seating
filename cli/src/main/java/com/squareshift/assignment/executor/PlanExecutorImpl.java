package com.squareshift.assignment.executor;

import static com.squareshift.assignment.constants.InputConstants.NUMBER_OF_PASSENGERS_DESCRIPTION;
import static com.squareshift.assignment.constants.InputConstants.SEAT_PLAN_DESCRIPTION;
import static com.squareshift.assignment.constants.StringConstants.EMPTY;

import com.google.inject.Inject;
import com.squareshift.assignment.model.Allocation;
import com.squareshift.assignment.model.Floor;
import com.squareshift.assignment.model.Input;
import com.squareshift.assignment.processor.InputProcessor;
import com.squareshift.assignment.processor.OutputProcessor;
import com.squareshift.assignment.service.AllocationService;
import com.squareshift.assignment.service.SeatingService;
import com.squareshift.assignment.utils.ScannerUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Log4j2
public class PlanExecutorImpl implements PlanExecutor {

  private final InputProcessor inputProcessor;
  private final SeatingService seatingService;
  private final AllocationService allocationService;
  private final OutputProcessor outputProcessor;
  private final ScannerUtils scannerUtils;

  @Inject
  public PlanExecutorImpl(
      InputProcessor inputProcessor,
      SeatingService seatingService,
      AllocationService allocationService,
      OutputProcessor outputProcessor,
      ScannerUtils scannerUtils) {
    this.inputProcessor = inputProcessor;
    this.seatingService = seatingService;
    this.allocationService = allocationService;
    this.outputProcessor = outputProcessor;
    this.scannerUtils = scannerUtils;
  }

  @Override
  public String execute(File file, PrintStream outputStream) throws IOException {

    String passengers;
    String seatPlan;

    outputStream.println(SEAT_PLAN_DESCRIPTION);
    seatPlan = scannerUtils.nextLine();
    outputStream.println(NUMBER_OF_PASSENGERS_DESCRIPTION);
    passengers = scannerUtils.nextLine();
    Input input = null;
    try {
      input = inputProcessor.processInput(seatPlan, passengers);
    } catch (Exception e) {
      log.error(e);
      outputStream.println("Invalid Input SeatPlan " + seatPlan + " or " + passengers);
      return EMPTY;
    }

    assert input != null;
    Floor floor = getFloor(input);
    Allocation allocation = getAllocation(floor, input);
    String output = write(allocation, file);
    outputStream.println("out-put file is : " + output);
    return output;
  }

  private Floor getFloor(Input input) {
    return seatingService.generateFloor(input.getSeatPlan());
  }

  private Allocation getAllocation(Floor floor, Input input) {
    return allocationService.allocate(floor, input.getNumberOfPassengers());
  }

  private String write(Allocation allocation, File file) throws IOException {

    XSSFWorkbook workbook = outputProcessor.generatePlan(allocation);
    OutputStream outputStream = new FileOutputStream(file);

    workbook.write(outputStream);
    workbook.close();

    return file.toString();
  }
}
