package com.squareshift.assignment.utils;

import static com.squareshift.assignment.constants.StringConstants.COMMA;
import static com.squareshift.assignment.constants.StringConstants.DOUBLE_SQUARE_BRACKETS_LEFT;
import static com.squareshift.assignment.constants.StringConstants.DOUBLE_SQUARE_BRACKETS_RIGHT;
import static com.squareshift.assignment.constants.StringConstants.DOUBLE_SQUARE_BRACKETS_WITH_COMMA;
import static com.squareshift.assignment.constants.StringConstants.EMPTY;
import static com.squareshift.assignment.constants.StringConstants.SPACE;

import com.squareshift.assignment.exception.InvalidSeatInputException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SeatPlanUtilsImpl implements SeatPlanUtils {

  @Override
  public List<List<Integer>> generateSeatPlan(String seatPlan) {

    List<List<Integer>> gridList = new ArrayList<>();
    String[] grids = processSeatPlan(seatPlan);

    for (String grid : grids) {
      try {
        List<Integer> gridProps =
            Arrays.stream(grid.split(COMMA)).map(Integer::parseInt).collect(Collectors.toList());
        gridList.add(gridProps);
      } catch (Exception e) {
        log.error(e);
        throw new InvalidSeatInputException("Seat Plan input is invalid for  " + seatPlan);
      }
    }

    return gridList;
  }

  @Override
  public Integer[][] convertSeatPlan(List<List<Integer>> seatPlan) {

    return seatPlan.stream().map(e -> e.toArray(Integer[]::new)).toArray(Integer[][]::new);
  }

  private String[] processSeatPlan(String seatPlan) {

    try {
      return seatPlan
          .replace(SPACE, EMPTY)
          .replace(DOUBLE_SQUARE_BRACKETS_LEFT, EMPTY)
          .replace(DOUBLE_SQUARE_BRACKETS_RIGHT, EMPTY)
          .split(DOUBLE_SQUARE_BRACKETS_WITH_COMMA);

    } catch (Exception e) {
      log.error(e);
      throw new InvalidSeatInputException("Seat Plan input is invalid for  " + seatPlan);
    }
  }
}
