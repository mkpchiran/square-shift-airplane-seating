package com.squareshift.assignment.utils;

import com.google.inject.AbstractModule;
import java.util.Scanner;
import lombok.Generated;

@Generated
public class UtilsModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(SeatPlanUtils.class).to(SeatPlanUtilsImpl.class);
    bind(ScannerUtils.class).toInstance(new ScannerUtilsImpl(new Scanner(System.in)));
  }
}
