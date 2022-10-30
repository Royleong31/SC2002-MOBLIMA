package account;
import person.Employee;

public class EmployeeAccount extends Account {
  private Employee user;
  // TODO: Set access types for a given account


  EmployeeAccount(String email, String password, String name, String employeeId) {
    super(email, password);
    this.user = new Employee(name, employeeId);
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
