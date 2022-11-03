package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.lang.Integer;

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
   * Formats the date and time(in 24H) to a string
   * @return String The formatted date and time
   */
  public static String getDateTime() {
    SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMddHHmm");
    dateForm.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
    return dateForm.format(new java.util.Date());
  }
  
  /**
   * Gets the time in hours.
   * @return Int The hour in day (0-23).
   */
  public static int getTime(){
    SimpleDateFormat timeForm = new SimpleDateFormat("HH");
	timeForm.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
	return Integer.parseInt(timeForm.format(new java.util.Date()));
  }
  
  /**
   * Gets the day of the week.
   * @return Int The day number of week (1 = Monday, ..., 7 = Sunday).
   */
  public static int getDay(){
    SimpleDateFormat dayForm = new SimpleDateFormat("u");
	dayForm.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
	return Integer.parseInt(dayForm.format(new java.util.Date()));
  }
}
