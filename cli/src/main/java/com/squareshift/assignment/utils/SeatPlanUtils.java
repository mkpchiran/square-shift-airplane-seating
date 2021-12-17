package com.squareshift.assignment.utils;

import java.util.List;

public interface SeatPlanUtils {

  List<List<Integer>> generateSeatPlan(String seatPlan);

  Integer[][] convertSeatPlan(List<List<Integer>> seatPlan);
}
