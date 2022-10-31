package controller;

import java.util.ArrayList;

import model.Account.Account;

enum LogInStatus {
  LOGIN,
  MOVIE_GOER,
  ADMIN
}

enum AccountType {
  MOVIE_GOER,
  ADMIN
}

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class LoginManager {
  private Account currentAccount = null;
  private ArrayList<Account> usersArr;


  /**
   * @param username
   * @param password
   * @param accountType
   * @return the logged in account
   */
  public Account login(String username, String password, AccountType accountType) {
    // currentAccount = account;
    return null;
  }

  /**
   * Takes in username and password 
   * Creates an admin account if accountType == ADMIN, else creates a movie goer account
   * @param username
   * @param password
   * @return the new user account
   */
  public Account register(String username, String password, AccountType accountType) {
    // currentAccount = account;
    return null;
  }

  /**
   * Logs the user out
   */
  public void logout() {
    currentAccount = null;
  }

  /**
   * Returns the currently logged in account
   * @return Account
   */
  public Account getCurrentAccount() {
    return currentAccount;
  }

  /**
   * Return the type of the currently logged in user
   * @return
   */
  public LogInStatus getLoginStatus() {
    return LogInStatus.LOGIN;
  }
}