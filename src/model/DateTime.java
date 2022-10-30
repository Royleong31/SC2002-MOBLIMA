package model;

public class DateTime {
  private int day;
  private int month;
  private int year;
  private int hour;
  private int minute;

  // Can be used for public holidays as well
  // just set hour and minute to 0
  public DateTime(int day, int month, int year, int hour, int minute) {
    updateDate(day, month, year, hour, minute);
  }

  // TODO: Create an overloaded constructor and updateDate method that takes in a Java DateTime object and parses it into day, month, year
  // TODO: Add check for weekend (can be done with java default date time object i think, with that, it's easier to calculate price)

  public int getDay() {
    return day;
  }

  public int getMonth() {
    return month;
  }

  public int getYear() {
    return year;
  }

  public int getHour() {
    return hour;
  }

  public int getMinute() {
    return minute;
  }

  /**
   * Checks if it's a valid date
   * @param day
   * @param month
   * @param year
   */
  public void updateDate(int day, int month, int year, int hour, int minute) {
    this.day = day;
    this.month = month;
    this.year = year;
    this.hour = hour;
    this.minute = minute;
  }

  /**
   * formats the date to a string
   * @return formatted date
   */
  public String getDate() {
    return "";
  }
  
}
