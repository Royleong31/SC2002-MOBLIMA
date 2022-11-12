package utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains static function to create an ArrayList from an array
 * @author Roy Leong
 * @version 1.0
 * @since 2022-10-30
 */
public class Utils {
  /*
   * Creates a new ArrayList from the given array
   */
  public static <T> ArrayList<T> asArrayList(final T... values) {
    ArrayList<T> foo = new ArrayList<T>(Arrays.asList(values));
    foo.removeIf((T value) -> value.equals(null));
    return new ArrayList<T>(Arrays.asList(values));
  }
}
