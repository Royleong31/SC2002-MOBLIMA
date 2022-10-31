package controller;

import java.util.ArrayList;

import enums.AccountType;
import enums.LoginStatus;
import model.Account.Account;



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
   * Set currentAccount to the logged in account
   * @param username
   * @param password
   * @return the logged in account
   */
  public Account login(String username, String password) {
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
   * Should return LoginStatus.ADMIN if the type of currentAccount is an AdminAccount object
   * Should return LoginStatus.MOVIE_GOER if the type of currentAccount is  MovieGoerAccount object
   * Return the type of the currently logged in user
   * @return
   */
  public LoginStatus getLoginStatus() {
    return LoginStatus.LOGIN;
  }
}