package com.squareshift.assignment.service;

import com.google.inject.AbstractModule;
import lombok.Generated;

@Generated
public class ServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(AllocationService.class).to(AllocationServiceImpl.class);
    bind(SeatingService.class).to(SeatingServiceImpl.class);
  }
}
