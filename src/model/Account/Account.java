package model.Account;

/**
 * Contains the username and password as well as login methods.
 * User is registered upon creation of the account.
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public abstract class Account {
  /**
   * The username of the account.
   */
  private String username;
  
  /**
   * The password for this account
   * There is no getter for this as the password should never be able to be accessed out of this class
   */
  private String password;

  /**
   * Constructor for the Account class.
   * This is the register method
   * @param username
   * @param password
   */
  public Account(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * @return username
   */
  public String getUsername() {
    return username;
  }

  /**
   * 
   * @param username
   * @param password
   * @return the Account object of the logged in user
   */
  public Account login(String username, String password) {
    if (this.username.equals(username) && this.password.equals(password)) {
      return this;
    }
    return null;
  }
}
