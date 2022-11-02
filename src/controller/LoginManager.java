package controller;

import java.util.ArrayList;
import model.Account.*;

enum LogInStatus {
  LOGIN,
  MOVIE_GOER,
  ADMIN
}

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

  /**
   * Registers new staff/admin account, logged in after registering.
   * @param username This new account's username.
   * @param password This new account's password.
   * @param id This new account's id, used to create staffId. 
   * @return currentAccount The new staff account.
   */
     
  public Account register(String username, String password, String id) {
	
	if(checkIfExists(username)==null){
		
		currentAccount = new StaffAccount(username, password, "CS"+id);
		usersArr.add(currentAccount);
		return currentAccount;
	}
	throw new Exception("Error: username or account exists!");
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
  
  public Account register(String username, String password, String name, String phoneNumber, String email){
	
	if(checkIfExists(username)==null){

		currentAccount = new MovieGoerAccount(username, password, name, phoneNumber, email);
		usersArr.add(currentAccount);
		return currentAccount;
	}
	throw new Exception("Error: username or account exists!");
  }
  
  /**
   * Checks array of user accounts if the username is already used.
   * @return Account The account that matches input username.
   */
  public Account checkIfExists(String username){
	  for(Account check : usersArr){
		  if(username == check.getUsername())
			  return check;
	  }
	  return null;
  }

  /**
   * Log in to account, for both movie goer and staff.
   * @param username This user's username.
   * @param password This user's password.
   * @return currentAccount The logged in account.
   */
  public Account login(String username, String password) {  
	for(Account search : usersArr){
		if(search.login(username,password)!=null){
			currentAccount = search;
			return currentAccount;
		}
	}
	throw new Exception("Error: account not found!");	
  }

  /**
   * Logs the user out
   */
  public void logout() {
    currentAccount = null;
  }

  /**
   * Gets the current user's account.
   * @return Account This user's account.
   */
  public Account getCurrentAccount() {
    return currentAccount;
  }

  /**
   * Gets the login status of the current user.
   * @return LogInStatus The current state of the log in.
   */
  public LogInStatus getLoginStatus() {
	  if(currentAccount instanceof StaffAccount)
		  return LogInStatus.ADMIN;
	  
	  else if(currentAccount instanceof MovieGoerAccount)
		  return LogInStatus.MOVIE_GOER;
	  
	  return LogInStatus.LOGIN;
  }
}
