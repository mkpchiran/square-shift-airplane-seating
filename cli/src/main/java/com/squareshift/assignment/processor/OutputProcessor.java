package com.squareshift.assignment.processor;

import com.squareshift.assignment.model.Allocation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface OutputProcessor {

  XSSFWorkbook generatePlan(Allocation allocation);
}
