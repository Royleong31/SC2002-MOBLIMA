package model.Account;

/**
 * Contains the username and password as well as login methods.
 * User is registered upon creation of the account.
 *
 @author Roy Leong, Ryan Ng
 @version 1.1
 @since 2022-10-30
*/
public class Account {
  /**
   * The username of the account.
   * There will be no mutator for this.
   */
  private String username;
  
  /**
   * The password of the account.
   * There will be no mutator for this.
   * There is no accessor for this as the password should never be able to be accessed out of this class.
   */
  private String password;

  /**
   * Constructor for the Account class.
   * Register method for new Account.
   * @param username This Account's username.
   * @param password This Account's password.
   */
  public Account(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * Gets the username of the account.
   * @return This account's username.
   */
  public String getUsername() {
    return username;
  }
 
   /**
   * User authentication method for login process.
   * @param username This entry of username during login.
   * @param password This entry of password during login.
   * @return this The Account object of the logged in user if authentication successful.
   * @return null value If the login authentication unsuccessful.
   *		 	  		Can be due to non-existent Account or wrong password.
   */
  public Account login(String username, String password) {
    if (this.username.equals(username) && this.password.equals(password)) {
      return this;
    }
    return null;
  }
}