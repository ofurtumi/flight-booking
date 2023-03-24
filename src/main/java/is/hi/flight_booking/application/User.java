package is.hi.flight_booking.application;

public class User {
  private final String id;
  private final String name;

  public User(String uId, String uName) {
    id = uId;
    name = uName;
  }

  public String getName() {
    return this.name;
  }

  public String getId() {
    return this.id;
  }
}
