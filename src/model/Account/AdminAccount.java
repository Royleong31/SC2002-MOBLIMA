package model.Account;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong, Ryan Ng
 @version 1.1
 @since 2022-10-30
*/
public class StaffAccount extends Account {
  /**
   * The identifcation of the staff.
   * There will be no mutator for this.
   */
  private String staffId;
  
  /**
   * Constructor for the StaffAccount class.
   * This is the register method.
   * @param username This staff's username.
   *				 Inherited from Account superclass.
   * @param password This staff's password.
   * 				 Inherited from Account superclass.
   * @param staffId This staff's identifcation.
   */
  public AdminAccount(String username, String password, String staffId) {
    super(username, password);
    this.staffId = staffId;
  }

  /**
   * Gets the staffId of the StaffAccount.
   * @return staffId This StaffAccount's staffId.
  */
  public String getStaffId() {
    return staffId;
  }

}