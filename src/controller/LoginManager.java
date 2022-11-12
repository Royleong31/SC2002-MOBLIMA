package controller;

import java.util.ArrayList;

import constants.Constants;
import enums.LoginStatus;
import model.Account.*;


/**
 * Manager for login process
 * Aggregation relationship with Account class
 * Accounts are part of LoginManager
 *
 @author Roy Leong, Ryan Ng
 @version 1.1
 @since 2022-10-30
*/
public class LoginManager {
  /**
   * The current user and his/her status.
   * There will be no mutator for this.
   */
  private Account currentAccount = null;
  
  /**
   * The list of user accounts.
   */
  private ArrayList<Account> usersArr = new ArrayList<Account>();
  private ArrayList<String> usedStaffIds = new ArrayList<String>();

  /**
   * Registers new staff/admin account, logged in after registering.
   * @param username This new account's username.
   * @param password This new account's password.
   * @param id This new account's id, used to create staffId. 
   * @return currentAccount The new staff account.
   */
  public Account registerAdmin(String username, String password, String staffId, String code) throws Exception {
    
    if (!code.equals(Constants.STAFF_ID_CODE))
      throw new Exception("Error: Invalid code!");
    
    // needs to be a valid staff Id that is not used
    if (!Constants.APPROVED_STAFF_IDS.contains(staffId) || this.usedStaffIds.contains(staffId))
      throw new Exception("Error: Invalid staff id!");

    if (!this.isUsernameAvailable(username)) 
      throw new Exception("Error: username or account exists!");
		
		this.currentAccount = new AdminAccount(username, password, "CS" + staffId);
		this.usersArr.add(currentAccount);
		return currentAccount;
  }
  
    /**
   * Registers new movie goer account, logged in after registering.
   * @param username This new account's username.
   * @param password This new account's password.
   * @param name This new user's name.
   * @param phoneNumber This new user's phone number.
   * @param email This new user's email address.
   * @return currentAccount The new movie goer account.
   */
  public Account registerMovieGoer(String username, String password, String name, String phoneNumber, String email) throws Exception{
    if (!this.isUsernameAvailable(username))
      throw new Exception("Error: username or account exists!");

		this.currentAccount = new MovieGoerAccount(username, password, name, phoneNumber, email);
		this.usersArr.add(currentAccount);
		return this.currentAccount;
  }
  
  /**
   * Checks array of user accounts if the username is already used.
   * @return Account The account that matches input username.
   */
  public boolean isUsernameAvailable(String username){
	  for (Account check : this.usersArr){
		  if (username.equals(check.getUsername())) {
        return false;
      }
	  }

	  return true;
  }

  /**
   * Log in to account, for both movie goer and staff.
   * @param username This user's username.
   * @param password This user's password.
   * @return currentAccount The logged in account.
   */
  public Account login(String username, String password) throws Exception{  
    for (Account search : this.usersArr){
      if (search.login(username, password) != null) {
        this.currentAccount = search;
        return this.currentAccount;
      }
    }

	  throw new Exception("Error: account not found!");	
  }

  /**
   * Logs the user out
   */
  public void logout() {
    this.currentAccount = null;
  }

  public void returnToMainMenu() {
    this.currentAccount = new GuestAccount();
  }

  /**
   * Gets the current user's account.
   * @return Account This user's account.
   */
  public Account getCurrentAccount() {
    return this.currentAccount;
  }

  /**
   * Gets the login status of the current user.
   * @return LogInStatus The current state of the log in.
   */
  public LoginStatus getLoginStatus() {
	  if (this.currentAccount instanceof AdminAccount) {
      return LoginStatus.ADMIN;
    }
	  else if (this.currentAccount instanceof MovieGoerAccount) {
      return LoginStatus.MOVIE_GOER;
    }
    else if (this.currentAccount instanceof GuestAccount) {
      this.currentAccount = null; //set it back to null so the user can access the login console next time
		  return LoginStatus.GUEST;
    }
    else {
      this.currentAccount = null;
      return LoginStatus.LOGIN;
    }
  }
}