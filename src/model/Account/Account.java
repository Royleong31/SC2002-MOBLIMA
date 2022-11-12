package model.Account;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Contains the username and password as well as login methods.
 * User is registered upon creation of the account.
 *
 @author Roy Leong, Ryan Ng
 @version 1.1
 @since 2022-10-30
*/
public class Account {
  /**
   * The username of the account.
   * There will be no mutator for this.
   */
  private String username;
  
  /**
   * The password of the account.
   * There will be no mutator for this.
   * There is no accessor for this as the password should never be able to be accessed out of this class.
   */
  private String password;

  /**
   * Constructor for the Account class, used to register new accounts.
   * Input password to be encrypted, only storing password hash. 
   * @param username This Account's username.
   * @param password This Account's password.
   */
  public Account(String username, String password) {
    try {
      this.username = username;
      this.password = getHexString(passwordToSHA(password));
    }
    catch (NoSuchAlgorithmException e){
      System.out.println("Exception thrown for incorrect algorithm: " + e);
    }
  }

  /**
   * Gets the username of the account.
   * @return This account's username.
   */
  public String getUsername() {
    return username;
  }
 
   /**
   * User authentication method for login process.
   * @param username This entry of username during login.
   * @param password This entry of password during login.
   * @return this The Account object of the logged in user if authentication successful.
   * @return null value If the login authentication unsuccessful.
   *		 	  		Can be due to non-existent Account or wrong password.
   */
  public Account login(String username, String password) {
    try{
      if (this.username.equals(username) && this.password.equals(getHexString(passwordToSHA(password)))) {
        return this;
      }
    }
    catch (NoSuchAlgorithmException e){
      System.out.println("Exception thrown for incorrect algorithm: " + e);
    }
    return null;
  }
  
 /**
   * Gets SHA.
   * @param password The password to be hashed.
   * @return byte[] The message digest.
   */
  private byte[] passwordToSHA(String password) throws NoSuchAlgorithmException{
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    return md.digest(password.getBytes(StandardCharsets.UTF_8));
  }
  
 /**
   * Gets the hexadecimal string of the password hash.
   * @param hash The array of bytes of message digest.
   * @return String The password hash,in hexadecimal form.
   */
  private String getHexString(byte[] hash){
    BigInteger num = new BigInteger(1, hash);
    StringBuilder hexString = new StringBuilder(num.toString(16));
	
    while (hexString.length() < 64) {
      hexString.insert(0, '0');
    }

	  return hexString.toString();
  }	
}
