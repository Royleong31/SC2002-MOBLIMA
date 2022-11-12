package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


public class DateTime {
  private int year;
  private int month;
  private int day;
  private int hour;
  private int minute;

  public DateTime(int year, int month, int day, int hour, int minute) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
  }

  public DateTime(int year, int month, int day) {
    this(year, month, day, 0, 0);
  }

  public LocalDateTime getDateTimeObj() throws ParseException {
    LocalDateTime dateTime = LocalDateTime.of(this.year, this.month, this.day, this.hour, this.minute);
    return dateTime;
  }

  public static DateTime getDateTime() {
    LocalDateTime lt = LocalDateTime.now();
    DateTime dateTime = new DateTime(lt.getYear(), lt.getMonthValue(), lt.getDayOfMonth(), lt.getHour(), lt.getMinute());
    
    return dateTime;
  }

  public String getDateTimeString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm");
    LocalDateTime dateTime = LocalDateTime.of(this.year, this.month, this.day, this.hour, this.minute);
    String formattedDateTime = dateTime.format(formatter);

    return formattedDateTime;
  }

  public int getYear() {
    return this.year;
  }

  public int getMonth() {
    return this.month;
  }

  public int getDay() {
    return this.day;
  }

  public int getHour() {
    return this.hour;
  }

  public int getMinute() {
    return this.minute;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public void setMinute(int minute) {
    this.minute = hour;
  }

  public int getDayOfWeek() throws ParseException {
    Date actualDateTime = new SimpleDateFormat("dd.MM.yyyy.HH.mm").parse(this.getDateTimeString());
    SimpleDateFormat dayForm = new SimpleDateFormat("u");
    return Integer.parseInt(dayForm.format(actualDateTime));
  }

  public DateTime getEveOfDate() {
    LocalDateTime date = LocalDateTime.of(this.year, this.month, this.day, this.hour, this.minute);
    LocalDateTime eve = date.minusHours(24); //does not edit the original date

    return new DateTime(eve.getYear(), eve.getMonthValue(), eve.getDayOfMonth(), eve.getHour(), eve.getMinute());
  }

  public boolean isDayEqual(DateTime date) {
    return this.year == date.getYear() && this.month == date.getMonth() && this.day == date.getDay();
  }

  public boolean isEqual(DateTime date) {
    return this.year == date.getYear() && this.month == date.getMonth() && this.day == date.getDay() && this.hour == date.getHour() && this.minute == date.getMinute();
  }
}
 