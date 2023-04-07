package is.hi.flight_booking.application;

public class User {
  private final String id;
  private final String name;

  public User(String userID, String userName) {
    id = userID;
    name = userName;
  }

  public String getName() {
    return this.name;
  }

  public String getId() {
    return this.id;
  }

  public String[] getInfo() {
    return new String[] { this.id, this.name };
  }

  @Override
  public String toString() {
    return String.format("User: %s | Name: %s", this.id, this.name);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }

    User u = (User) o;
    return this.id.equals(u.getId()) && this.name.equals(u.getName());
  }
}
