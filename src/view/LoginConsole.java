package view;

import controller.LoginManager;
import model.Account.Account;
import utils.Utils;

/**
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class LoginConsole extends ParentConsole {
  private final LoginManager loginManager;
  
  public LoginConsole(LoginManager loginManager) {
    this.loginManager = loginManager;
  }
  
  @Override
  public void display(Account account) { // account is unused as it's null
    String userSelection = this.getUserChoice("Enter '1' to log in, '2' to register, '3' to exit", Utils.asArrayList("1", "2", "3"));

    if (userSelection == "1") {
      this.login();
    } else if (userSelection == "2") {
      this.register();
    } else if (userSelection == "3") {
      this.exitProgram();
    } else {
      // Should never reach here as error checking is done in this.getUserChoice()
      throw new Error("An unexpected error occured");
    }
  }

  /**
   * Asks the user for login details and logs them in
   * @return
   */
  private void login() {
    while (true) {
      try {
        String username = super.getUserInput("Username: ");
        String password = super.getUserInput("Password: ");
        loginManager.login(username, password);
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        continue;
      }
    }
  }

  /**
   * Asks the user for registration details and registers them
   * @return
   */
  private void register() {
    while (true) {
      try {
        String username = super.getUserInput("Username: ");
        String password = super.getUserInput("Password: ");
        Boolean isAdmin = this.getUserChoice("Are you an admin? (y/n)", Utils.asArrayList("y", "n")).equals("y");

        if (isAdmin) {
          String staffId = super.getUserInput("Staff ID: ");
          this.loginManager.registerAdmin(username, password, staffId);
        } else {
          String name = super.getUserInput("Name: ");
          String phoneNumber = super.getUserInput("Phone Number: ");
          String email = super.getUserInput("Email: ");
          this.loginManager.registerMovieGoer(username, password, name, phoneNumber, email);
        }

      } catch (Exception e) {
        System.out.println(e.getMessage());
        continue;
      }
    }
  }
}

/**
 * Problem 1: User input checking is repeated multiple times
 * Create a helper function in the parent class that takes in a list of valid inputs and a prompt
 * Will only return the user's input, user can't quit
 * 
 * Problem 2: How to recognise that the user quits the program?
 * Create a function in the ParentConsole that handles quitting of a program that will store all state in storage and then call System.exit(0);
 */