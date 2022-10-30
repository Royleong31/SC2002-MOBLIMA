package account;

enum AccessTypes {
  READ_OWN_DATA,
  READ_OTHER_DATA,
  WRITE_OWN_DATA,
  WRITE_OTHER_DATA,
  READ_MOVIE_DATA,
  WRITE_MOVIE_DATA,
}

// need to have login and register methods
// loggedIn status
// store the email and password
// type of account enum
abstract public class Account {
  private String email;
  private String password;
  private boolean isLoggedIn = false;
  private AccessTypes[] accessTypes;
  
  // creating an account is the register method
  Account(String email, String password, AccessTypes[] accessTypes) {
    // check if the email and password is correct
    // if correct, set loggedIn to true
    this.email = email;
    this.password = password;
    this.isLoggedIn = true; // log user in upon registering
    this.accessTypes = accessTypes;
  }

  // return value returns whether the login was successful
  boolean login(String email, String password) {
    // if alr logged in, just set isLoggedIn to true
    // should never reach this point as login should be disabled when user is alr logged in
    if (this.isLoggedIn) return true;

    // check if the email and password is correct
    // if correct, set loggedIn to true
    if (email == this.email && password == this.password) {
      this.isLoggedIn = true;
      return true;
    } else {
      System.out.println("Invalid email or password");
      return false;
    }
  }


  void logOut() {
    this.isLoggedIn = false;
  }

  public boolean isLoggedIn() {
    return this.isLoggedIn;
  }

  public String getEmail() {
    return this.email;
  }

  // TODO: Confirm the access modifiers for each method
  abstract public boolean canAccess(AccessTypes accessType);
}
