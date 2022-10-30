package person;
// abstract as it can't be instantiated on its own, only its children can
public abstract class Person {
  private String name;

  // TODO: Confirm the access modifiers for each method
  public Person(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
