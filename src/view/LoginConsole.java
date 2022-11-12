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
    super();
    this.loginManager = loginManager;
  }
  
  @Override
  public void display(Account account) { // account is unused as it's null
    System.out.println("Inside login console");
    // TODO: Use standard input to get user input
    Integer userSelection = this.getUserChoiceFromCount("Enter '1' to log in, '2' to register, '3' to return to main menu", 3);

    if (userSelection == 1) {
      this.login();
    } else if (userSelection == 2) {
      this.register();
    } else if (userSelection == 3) {
      //this.exitProgram();
      loginManager.returnToMainMenu();
      return;
    } else {
      // Should never reach here as error checking is done in this.getUserChoice()
      System.out.println("An unexpected error occured");
      this.exitProgram();
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
        System.out.println("Successfully logged in!");
        return;
      } catch (Exception e) {
        System.out.println("Error in logging in");
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
          String staffCode = super.getUserInput("Staff Code: ");
          this.loginManager.registerAdmin(username, password, staffId, staffCode);
          break;
        } else {
          String name = super.getUserInput("Name: ");
          String phoneNumber = super.getUserInput("Phone Number: ");
          String email = super.getUserInput("Email: ");
          this.loginManager.registerMovieGoer(username, password, name, phoneNumber, email);
          break;
        }

      } catch (Exception e) {
        System.out.println(e.getMessage());
        continue;
      }
    }

    System.out.println("Successfully registered!");
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