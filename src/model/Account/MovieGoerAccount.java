package model.Account;

/**
 * Account for a movie goer.
 * Contains movie goer specific logic like name, phone number and email
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class MovieGoerAccount extends Account {
  /**
   * The name of the movie goer
   */
  private String name;

  /**
   * The phone number of the movie goer
   */
  private String phoneNumber;

  /**
   * The email of the movie goer
   */
  private String email;
  
  /**
   * Constructor for the MovieGoerAccount class.
   * This is the register method
   * @param username
   * @param password
   * @param name
   * @param phoneNumber
   * @param email
   */
  public MovieGoerAccount(String username, String password, String name, String phoneNumber, String email) {
    super(username, password);
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  /**
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * @return name
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * @return name
   */
  public String getEmail() {
    return email;
  }
  
}
