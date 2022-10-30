package model;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class Holiday {
  private DateTime date;

  /**
   * Price multiplier for this date
   */
  private float priceMultiplier;

  public Holiday(DateTime date, float priceMultiplier) {
    this.date = date;
    this.priceMultiplier = priceMultiplier;
  }

  public String getDate() {
    return date.getDate();
  }

  /**
   * Update the date of this holiday
   */
  public void updateDate(int day, int month, int year) {
    date.updateDate(day, month, year, 0, 0);
  }
}
