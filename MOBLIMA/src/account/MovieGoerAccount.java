package account;
import person.MovieGoer;

public class MovieGoerAccount extends Account {
  private MovieGoer user;

  MovieGoerAccount(String email, String password, String name, String phoneNumber, String email) {
    super(email, password);
    this.user = new MovieGoer(name, phoneNumber, email);
  }

  @Override
  public boolean canAccess(AccessTypes accessType) {
    if (!super.isLoggedIn()) return false;
    switch (accessType) {
      case READ_OWN_DATA:
      case WRITE_OWN_DATA:
      case READ_MOVIE_DATA:
        return true;
      default:
        return false;
    }
  }
}
