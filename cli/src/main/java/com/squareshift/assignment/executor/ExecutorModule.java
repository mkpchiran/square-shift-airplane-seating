package com.squareshift.assignment.executor;

import com.google.inject.AbstractModule;
import lombok.Generated;

@Generated
public class ExecutorModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(PlanExecutor.class).to(PlanExecutorImpl.class);
  }
}
