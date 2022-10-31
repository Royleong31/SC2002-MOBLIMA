package model.Account;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class AdminAccount extends Account {
  private String staffId;

  /**
   * Constructor for the StaffAccount class.
   * This is the register method
   * @param username
   * @param password
   * @param staffId
   */
  public AdminAccount(String username, String password, String staffId) {
    super(username, password);
    this.staffId = staffId;
  }

  /**
   * @return staffId
  */
  public String getStaffId() {
    return staffId;
  }

  /**
   * @param staffId
   */
  public void setStaffId(String staffId) {
    this.staffId = staffId;
  }
}
