package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.lang.Integer;

/**
 * Date and time formatter.
 *
 @author Roy Leong, Ryan Ng
 @version 1.1
 @since 2022-10-30
*/
public class DateTimeUtils {

  /**
   * Gets the realtime(SGT, GMT+8) date and time(in 24H) to a string
   * @return String The formatted date and time.
   */
  public static String getDateTime() {
    SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMddHHmm");
    dateForm.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
    return dateForm.format(new java.util.Date());
  }
  
  /**
   * @param String The date and time in dd.MM.yyyy.HH.mm format.
   *        (eg. 31 Dec 1998 7.30pm == 31.12.1998.19.30)
   * @return Int The integer value of day (1 = Monday, ..., 7 = Sunday).
   */
  public static int dateTimeToDay(String stringDateTime) throws Exception{
	Date actualDateTime = new SimpleDateFormat("dd.MM.yyyy.HH.mm").parse(stringDateTime);
	SimpleDateFormat dayForm = new SimpleDateFormat("u");
	return Integer.parseInt(dayForm.format(actualDateTime));
  }
  
  /**
   * @param String The date and time in dd.MM.yyyy.HH.mm format.
   *        (eg. 31 Dec 1998 7.30pm == 31.12.1998.19.30)
   * @return Int The integer value of date.
   *        (eg. Input: 05.01.1999.18.30 >> Output:5)
   */
  public static int dateTimeToDate(String stringDateTime) throws Exception{
	Date actualDateTime = new SimpleDateFormat("dd.MM.yyyy.HH.mm").parse(stringDateTime);
	SimpleDateFormat dateForm = new SimpleDateFormat("dd");
	return Integer.parseInt(dateForm.format(actualDateTime));
  }
  
  /**
   * @param String The date and time in dd.MM.yyyy.HH.mm format.
   *        (eg. 31 Dec 1998 7.30pm == 31.12.1998.19.30)
   * @return Int The integer value of month. 
   *        (eg. Input: 31.01.1999.18.30 >> Output:1)
   */
  public static int dateTimeToMonth(String stringDateTime) throws Exception{
	Date actualDateTime = new SimpleDateFormat("dd.MM.yyyy.HH.mm").parse(stringDateTime);
	SimpleDateFormat monthForm = new SimpleDateFormat("MM");
	return Integer.parseInt(monthForm.format(actualDateTime));
  }
  
  /**
   * @param String The date and time in dd.MM.yyyy.HH.mm format.
   *        (eg. 31 Dec 1998 7.30pm == 31.12.1998.19.30)
   * @return Int The integer value of year (yyyy)
   */
  public static int dateTimeToYear(String stringDateTime) throws Exception{
	Date actualDateTime = new SimpleDateFormat("dd.MM.yyyy.HH.mm").parse(stringDateTime);
	SimpleDateFormat yearForm = new SimpleDateFormat("yyyy");
	return Integer.parseInt(yearForm.format(actualDateTime));
  }

  /**
   * @param String The date and time in dd.MM.yyyy.HH.mm format 
   *        (eg. 31 Dec 1998 7.30pm == 31.12.1998.19.30)
   * @return Int The integer value of hour in day (0-23).
   *        (eg. Input: 31.12.1998.09.30 >> Output: 9)
   */
  public static int dateTimeToHour(String stringDateTime) throws Exception{
	Date actualDateTime = new SimpleDateFormat("dd.MM.yyyy.HH.mm").parse(stringDateTime);
	SimpleDateFormat hourForm = new SimpleDateFormat("HH");
	return Integer.parseInt(hourForm.format(actualDateTime));
  }
  
  /**
   * @param String The date and time in dd.MM.yyyy.HH.mm format 
   *        (eg. 31 Dec 1998 7.30pm == 31.12.1998.19.30)
   * @return Int The integer value of minute (0-60).
   *        (eg. Input: 31.12.1998.09.05 >> Output: 5)
   */
  public static int dateTimeToMinute(String stringDateTime) throws Exception{
	Date actualDateTime = new SimpleDateFormat("dd.MM.yyyy.HH.mm").parse(stringDateTime);
	SimpleDateFormat minuteForm = new SimpleDateFormat("mm");
	return Integer.parseInt(minuteForm.format(actualDateTime));
  }
  
  
  
}