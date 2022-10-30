package person;
public class MovieGoer extends Person {
  private String phoneNumber;
  private String email;

  public MovieGoer(String name, String phoneNumber, String email) {
    super(name);
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  protected String getPhoneNumber() {
    return this.phoneNumber;
  }

  void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  protected String getEmail() {
    return this.email;
  }

  void setEmail(String email) {
    this.email = email;
  }

}
