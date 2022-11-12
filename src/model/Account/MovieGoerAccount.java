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
   * Constructor for the MovieGoerAccount class, used to register new movie goer accounts.
   * @param username is the new movie goer's username, inherited from Account superclass.
   * @param password is the new movie goer's password, inherited from the Account superclass.
   * @param name is the new movie goer's name.
   * @param phoneNumber is the new movie goer's phone number.
   * @param email is the new movie goer's email address.
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
