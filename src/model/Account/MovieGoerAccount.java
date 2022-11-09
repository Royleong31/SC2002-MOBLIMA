package model.Account;

/**
 * Account for a movie goer.
 * Contains movie goer specific logic like name, phone number and email
 *
 @author Roy Leong, Ryan Ng
 @version 1.1
 @since 2022-10-30
*/
public class MovieGoerAccount extends Account {
  /**
   * The name of the movie goer.
   * There will be no mutator for this.
   */
  private String name;

  /**
   * The phone number of the movie goer.
   * There will be no mutator for this.
   */
  private String phoneNumber;

  /**
   * The email of the movie goer.
   * There will be no mutator for this.
   */
  private String email;
  
  /**
   * Constructor for the MovieGoerAccount class.
   * This is the register method.
   * @param username This is the movie goer's username.
   *				 Inherited from Account superclass.
   * @param password This is the movie goer's password.
   *				 Inherited from the Account superclass.
   * @param name This is the movie goer's name.
   * @param phoneNumber This is the movie goer's phone number.
   * @param email This is the movie goer's email address.
   */
  public MovieGoerAccount(String username, String password, String name, String phoneNumber, String email) {
    super(username, password);
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  /**
   * Gets the name of the MovieGoerAccount.
   * @return name This MovieGoerAccount's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the phoneNumber of the MovieGoerAccount.
   * @return phoneNumber This MovieGoerAccount's phoneNumber.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Gets the email of the MovieGoerAccount.
   * @return email This MovieGoerAccount's email.
   */
  public String getEmail() {
    return email;
  }
}