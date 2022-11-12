package view;

import controller.LoginManager;
import model.Account.Account;
import utils.Utils;

/**
 * Allows the non-logged in users to view movies and screenings
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class LoginConsole extends ParentConsole {
  /**
   * Log in status is stored in the login manager
   */
  private final LoginManager loginManager;
  
  /**
   * Constructor for the login manager
   * @param loginManager
   */
  public LoginConsole(LoginManager loginManager) {
    super();
    this.loginManager = loginManager;
  }

  /**
   * Asks the user for login details and logs them in
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
  /**
   * Allows the user to choose which option they want to do
   * account is null here because the user is not logged in
   * @param account
   */
  @Override
  public void display(Account account) { // account is unused as it's null
    System.out.println("Inside login console");
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
    }
  }
}