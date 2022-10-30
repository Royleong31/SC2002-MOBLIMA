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
  private Date date;
  private float multiplier;

  public Holiday(Date date, float multiplier) {
    this.date = date;
    this.multiplier = multiplier;
  }

  public Date getDate() {
    return date;
  }

  public void updateDate() {
    
  }
}
