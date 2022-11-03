package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Utils {
  /*
   * Creates a new ArrayList from the given array
   */
  // TODO: Use generics instead of String
  public static <T> ArrayList<T> asArrayList(T... values) {
    ArrayList<T> foo = new ArrayList<T>(Arrays.asList(values));
    foo.removeIf((T value) -> value == null);
    return new ArrayList<T>(Arrays.asList(values));
  }

}
