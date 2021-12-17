package com.squareshift.assignment.helper;

import com.google.inject.AbstractModule;
import lombok.Generated;

@Generated
public class HelperModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ValidationHelper.class).to(ValidationHelperImpl.class);
  }
}
