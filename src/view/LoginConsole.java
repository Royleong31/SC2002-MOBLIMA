package view;
import controller.LoginManager;
import model.Account.Account;

/**
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class LoginConsole extends ParentConsole {
  /**
   * Handles the login related logic and state
   * Instantiated in main so the state can be shared with other console classes
   */
  private final LoginManager loginManager;

  public LoginConsole(LoginManager loginManager) {
    super();
    this.loginManager = loginManager;
  }

  /**
   * Asks the user for login details and logs them in
   * @return
   */
  public void login() {
  }

  /**
   * Asks the user for registration details and registers them
   * @return
   */
  public void register() {
  }
}
