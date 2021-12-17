package com.squareshift.assignment;

import com.google.inject.AbstractModule;
import com.squareshift.assignment.executor.ExecutorModule;
import com.squareshift.assignment.helper.HelperModule;
import com.squareshift.assignment.processor.ProcessorModule;
import com.squareshift.assignment.service.ServiceModule;
import com.squareshift.assignment.utils.UtilsModule;
import lombok.Generated;

@Generated
public class ApplicationModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new ProcessorModule());
    install(new UtilsModule());
    install(new ExecutorModule());
    install(new ServiceModule());
    install(new HelperModule());
  }
}
