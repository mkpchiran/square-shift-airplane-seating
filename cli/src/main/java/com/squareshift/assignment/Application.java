package com.squareshift.assignment;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.squareshift.assignment.executor.PlanExecutor;
import java.io.File;
import java.io.IOException;
import lombok.Generated;

@Generated
public class Application {

  public static void main(String[] args) throws IOException {
    Injector injector = Guice.createInjector(Stage.PRODUCTION, new ApplicationModule());
    PlanExecutor executor = injector.getInstance(PlanExecutor.class);
    executor.execute(File.createTempFile("passenger-plan-", ".xlsx"), System.out);
  }
}
