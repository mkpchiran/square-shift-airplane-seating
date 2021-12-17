package com.squareshift.assignment.processor;

import static com.squareshift.assignment.constants.ReportConstants.AISLE;
import static com.squareshift.assignment.constants.ReportConstants.MIDDLE;
import static com.squareshift.assignment.constants.ReportConstants.PENDING_PASSENGERS;
import static com.squareshift.assignment.constants.ReportConstants.SHEET_NAME;
import static com.squareshift.assignment.constants.ReportConstants.WINDOW;
import static com.squareshift.assignment.constants.StringConstants.EMPTY;

import com.squareshift.assignment.model.Allocation;
import com.squareshift.assignment.model.Seat;
import com.squareshift.assignment.model.SeatGroup;
import com.squareshift.assignment.model.SeatType;
import java.util.List;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class OutputProcessorImpl implements OutputProcessor {

  @Override
  public XSSFWorkbook generatePlan(Allocation allocation) {

    List<SeatGroup> seatGroups = allocation.getSeatGroups();

    XSSFWorkbook workbook = new XSSFWorkbook();

    XSSFSheet sheet = workbook.createSheet(SHEET_NAME);

    int startColumn = 0;
    int startRow = sheet.getLastRowNum() + 1;
    for (SeatGroup seatGroup : seatGroups) {
      generateGrid(sheet, startColumn, startRow, seatGroup);
      startColumn = sheet.getRow(startRow).getLastCellNum() + 1;
    }

    if (allocation.getPending() > 0) {
      generatePending(sheet, allocation.getPending());
    }

    generateLegend(sheet);
    return workbook;
  }

  private void generateGrid(XSSFSheet sheet, int startColumn, int startRow, SeatGroup seatGroup) {

    for (int i = 0; i < seatGroup.getSeats().size(); i++) {

      Seat seat = seatGroup.getSeats().get(i);
      XSSFRow row = sheet.getRow(startRow + seat.getLocation().getRowIndex());
      row = row == null ? sheet.createRow(seat.getLocation().getRowIndex()) : row;
      XSSFCell cell =
          row.getCell(
              startColumn + seat.getLocation().getColumnIndex(),
              MissingCellPolicy.CREATE_NULL_AS_BLANK);
      if (seat.getPassengerIndex() > 0) {
        cell.setCellValue(seat.getPassengerIndex());
      }
      XSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
      XSSFFont font = sheet.getWorkbook().createFont();
      cell.setCellStyle(setFont(setBoarders(setColors(seat.getType(), cellStyle)), font));
    }
  }

  private void generatePending(XSSFSheet sheet, int pending) {
    XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
    XSSFCell cell = row.createCell(0);
    cell.setCellValue(PENDING_PASSENGERS);
    XSSFCell cell1 = row.createCell(1);
    cell1.setCellValue(pending);
  }

  private void generateLegend(XSSFSheet sheet) {
    XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
    XSSFCell cell = row.createCell(0);
    cell.setCellValue(AISLE);
    cell = row.createCell(1);
    cell.setCellStyle(setColors(SeatType.AISEL, sheet.getWorkbook().createCellStyle()));
    cell.setCellValue(EMPTY);
    row = sheet.createRow(sheet.getLastRowNum() + 1);
    cell = row.createCell(0);
    cell.setCellValue(MIDDLE);
    cell = row.createCell(1);
    cell.setCellValue(EMPTY);
    cell.setCellStyle(setColors(SeatType.MIDDLE, sheet.getWorkbook().createCellStyle()));

    row = sheet.createRow(sheet.getLastRowNum() + 1);
    cell = row.createCell(0);
    cell.setCellValue(WINDOW);
    cell = row.createCell(1);
    cell.setCellValue(EMPTY);
    cell.setCellStyle(setColors(SeatType.WINDOW, sheet.getWorkbook().createCellStyle()));
  }

  private CellStyle setColors(SeatType type, CellStyle cellStyle) {

    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    switch (type) {
      case AISEL:
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        break;
      case WINDOW:
        cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        break;
      case MIDDLE:
      default:
        cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        break;
    }
    return cellStyle;
  }

  private CellStyle setBoarders(CellStyle cellStyle) {
    cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    cellStyle.setBorderBottom(BorderStyle.THIN);

    cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
    cellStyle.setBorderTop(BorderStyle.THIN);

    cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
    cellStyle.setBorderLeft(BorderStyle.THIN);

    cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
    cellStyle.setBorderRight(BorderStyle.THIN);

    return cellStyle;
  }

  private CellStyle setFont(CellStyle cellStyle, Font font) {
    font.setColor(HSSFColorPredefined.WHITE.getIndex());
    cellStyle.setFont(font);
    return cellStyle;
  }
}
