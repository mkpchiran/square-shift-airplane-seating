package com.squareshift.assignment.executor;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public interface PlanExecutor {

  String execute(File file, PrintStream outputStream) throws IOException;
}
