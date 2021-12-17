package com.squareshift.assignment.processor;

import com.google.inject.AbstractModule;
import lombok.Generated;

@Generated
public class ProcessorModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(InputProcessor.class).to(InputProcessorImpl.class);
    bind(OutputProcessor.class).to(OutputProcessorImpl.class);
  }
}
