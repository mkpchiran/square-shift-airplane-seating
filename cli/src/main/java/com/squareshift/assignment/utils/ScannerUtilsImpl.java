package com.squareshift.assignment.utils;

import java.util.Scanner;

public class ScannerUtilsImpl implements ScannerUtils {

  private final Scanner scanner;

  public ScannerUtilsImpl(Scanner scanner) {
    this.scanner = scanner;
  }

  @Override
  public String nextLine() {
    return this.scanner.nextLine();
  }
}
