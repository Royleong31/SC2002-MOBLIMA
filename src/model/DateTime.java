package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.io.Serializable;

/**
 * DateTime class
 * Contains details of the Date and Time and get/set functions
 *
 @author Roy Leong, Song Chen
 @version 1.1
 @since 2022-10-30
*/
public class DateTime implements Serializable {
  /**
   * Year of the date
   */
  private int year;

  /**
   * Month of the date
   */
  private int month;

  /**
   * Day of the date
   */
  private int day;

  /**
   * Hour of the time
   */
  private int hour;

  /**
   * Minute of the time
   */
  private int minute;

  /**
   * Constructor for DateTime
   * 
   * @param year year of the date
   * @param month month of the date
   * @param day day of the date
   * @param hour hour of the time
   * @param minute minute of the time
   */
  public DateTime(int year, int month, int day, int hour, int minute) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
  }

  /**
   * Overloaded constructor for DateTime, uses default values of 0 for hour and minute
   * 
   * @see DateTime#DateTime(int, int, int, int, int)
   */
  public DateTime(int year, int month, int day) {
    this(year, month, day, 0, 0);
  }

  /**
   * Get a LocalDateTime object from this object's attributes
   * 
   * @return LocalDateTime object with this object's attributes
   * @throws ParseException if parameters passed to the LocalDateTime object is invalid
   */
  public LocalDateTime getDateTimeObj() throws ParseException {
    LocalDateTime dateTime = LocalDateTime.of(this.year, this.month, this.day, this.hour, this.minute);
    return dateTime;
  }

  /**
   * Get a DateTime object based on the current time and date
   * 
   * @return DateTime object based on the current time and date
   */
  public static DateTime getDateTime() {
    LocalDateTime lt = LocalDateTime.now();
    DateTime dateTime = new DateTime(lt.getYear(), lt.getMonthValue(), lt.getDayOfMonth(), lt.getHour(), lt.getMinute());
    
    return dateTime;
  }

  /**
   * Get a DateTime string of a specific format (dd.MM.yyyy.HH.mm) based on the attributes of the object
   * 
   * @return DateTime string based on the attributes of the object
   */
  public String getDateTimeString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm");
    LocalDateTime dateTime = LocalDateTime.of(this.year, this.month, this.day, this.hour, this.minute);
    String formattedDateTime = dateTime.format(formatter);

    return formattedDateTime;
  }

  /**
   * Getter method to get the year of the DateTime object
   * 
   * @return year of the DateTime object
   */
  public int getYear() {
    return this.year;
  }

  /**
   * Getter method to get the month of the DateTime object
   * 
   * @return month of the DateTime object
   */
  public int getMonth() {
    return this.month;
  }

  /**
   * Getter method to get the day of the DateTime object
   * 
   * @return day of the DateTime object
   */
  public int getDay() {
    return this.day;
  }

  /**
   * Getter method to get the hour of the DateTime object
   * 
   * @return hour of the DateTime object
   */
  public int getHour() {
    return this.hour;
  }

  /**
   * Getter method to get the minute of the DateTime object
   * 
   * @return minute of the DateTime object
   */
  public int getMinute() {
    return this.minute;
  }

  /**
   * Setter method to set the year of the DateTime object
   * 
   * @param year year of the DateTime object
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * Setter method to set the month of the DateTime object
   * 
   * @param month month of the DateTime object
   */
  public void setMonth(int month) {
    this.month = month;
  }

  /**
   * Setter method to set the day of the DateTime object
   * 
   * @param day day of the DateTime object
   */
  public void setDay(int day) {
    this.day = day;
  }

  /**
   * Setter method to set the hour of the DateTime object
   * 
   * @param hour hour of the DateTime object
   */
  public void setHour(int hour) {
    this.hour = hour;
  }

  /**
   * Setter method to set the minute of the DateTime object
   * 
   * @param minute minute of the DateTime object
   */
  public void setMinute(int minute) {
    this.minute = hour;
  }

  /**
   * Get the day number of week i.e 1 = Monday, ..., 7 = Sunday from the date time of this object
   * 
   * @return day number of week
   * @throws ParseException if date time string recieved is of an invalid format
   */
  public int getDayOfWeek() throws ParseException {
    Date actualDateTime = new SimpleDateFormat("dd.MM.yyyy.HH.mm").parse(this.getDateTimeString());
    SimpleDateFormat dayForm = new SimpleDateFormat("u");
    return Integer.parseInt(dayForm.format(actualDateTime));
  }

  /**
   * Get the eve of the date time of this object
   * 
   * @return DateTime object of the eve of the date time of this object
   * @throws ParseException if date time string recieved is of an invalid format
   */
  public DateTime getEveOfDate() {
    LocalDateTime date = LocalDateTime.of(this.year, this.month, this.day, this.hour, this.minute);
    LocalDateTime eve = date.minusHours(24); //does not edit the original date

    return new DateTime(eve.getYear(), eve.getMonthValue(), eve.getDayOfMonth(), eve.getHour(), eve.getMinute());
  }

  /**
   * Compares the year, month and day of the current DateTime object with a provided DateTime object
   * 
   * @param date DateTime object to compare to
   * @return true if the year, month and day of the current DateTime object is the same as the provided DateTime object, false if otherwise 
   */
  public boolean isDayEqual(DateTime date) {
    return this.year == date.getYear() && this.month == date.getMonth() && this.day == date.getDay();
  }

  /**
   * Compares the year, month, day, hour and minute of the current DateTime object with a provided DateTime object
   * 
   * @param date DateTime object to compare to
   * @return true if the year, month, day, hour and minute of the current DateTime object is the same as the provided DateTime object, false if otherwise 
   */
  public boolean isEqual(DateTime date) {
    return this.year == date.getYear() && this.month == date.getMonth() && this.day == date.getDay() && this.hour == date.getHour() && this.minute == date.getMinute();
  }
}
 