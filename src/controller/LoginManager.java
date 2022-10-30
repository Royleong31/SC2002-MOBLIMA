package controller;

import java.util.ArrayList;

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

  public Account login(String username, String password) {
    return null;
  }

  public Account register(String username, String password) {
    return null;
  }

  public void logout() {
    currentAccount = null;
  }

  public Account getCurrentAccount() {
    return currentAccount;
  }

  public boolean isLoggedIn() {
    return currentAccount != null;
  }
}