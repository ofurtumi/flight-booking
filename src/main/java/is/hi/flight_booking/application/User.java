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
    return new String[]{this.id, this.name};
  }
}
