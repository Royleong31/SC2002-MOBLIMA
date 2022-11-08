package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;

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
}
