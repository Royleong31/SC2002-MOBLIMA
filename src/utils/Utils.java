package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Utils {
  /*
   * Creates a new ArrayList from the given array
   */
  public static ArrayList<String> asArrayList(String... values) {
    return new ArrayList<String>(Arrays.asList(values));
  }

  public static ArrayList<Integer> asArrayList(Integer... values) {
    return new ArrayList<Integer>(Arrays.asList(values));
  }

//   TODO: Create more overloaded functions if needed

}
