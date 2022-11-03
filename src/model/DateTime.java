package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Realtime date and time formatter
 * Formats according to Singapore Time, GMT+8
 *
 @author Roy Leong, Ryan Ng
 @version 1.1
 @since 2022-10-30
*/
public class DateTime {

  /**
   * Formats the date and time(in 24H) to a string.
   * @return String The formatted date and time.
   */
  public static String getDateTime() {
    SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMddHHmm");
    dateForm.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
    return dateForm.format(new java.util.Date());
  }
  
}
