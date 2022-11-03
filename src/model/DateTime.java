package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTime {

  /**
   * formats the date and time(in 24H) to a string
   * @return formatted date and time
   */
  public static String getDateTime() {
    SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMddHHmm");
    dateForm.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
    return dateForm.format(new java.util.Date());
  }
  
}
