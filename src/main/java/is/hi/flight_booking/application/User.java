package is.hi.flight_booking.application;

public class User {
<<<<<<< HEAD
    private final String id;
    private final String name;

    public User(String uId, String uName) {
        id = uId;
        name = uName;
    }
=======
  private String id;
  private String name;

  public User(String uId, String uName) {
    id = uId;
    name = uName;
  }
>>>>>>> c91721e5d035080a59c51614a2770d2b3d41d564

  public String getName() {
    return this.name;
  }

  public String getId() {
    return this.id;
  }
}
