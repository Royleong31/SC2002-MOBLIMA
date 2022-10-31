package view;
import java.util.Scanner;

import controller.LoginManager;
import enums.AccountType;
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
  
  public void display() {
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
    Scanner scannerObj = new Scanner(System.in);
    while (true) {
      try {
        System.out.print("Username: ");
        String username = scannerObj.nextLine();
        System.out.print("Password: ");
        String password = scannerObj.nextLine();
        loginManager.login(username, password);
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        continue;
      }
    }
    scannerObj.close();
  }

  /**
   * Asks the user for registration details and registers them
   * @return
   */
  private void register() {
    Scanner scannerObj = new Scanner(System.in);
    while (true) {
      try {
        System.out.print("Username: ");
        String username = scannerObj.nextLine();
        System.out.print("Password: ");
        String password = scannerObj.nextLine();
        Boolean isAdmin = this.getUserChoice("Are you an admin? (y/n)", Utils.asArrayList("y", "n")).equals("y");
        loginManager.register(username, password, isAdmin ? AccountType.ADMIN : AccountType.MOVIE_GOER);
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        continue;
      }
    }
    scannerObj.close();
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