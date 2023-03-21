package is.hi.flight_booking.application;

public class User {
    private String id;
    private String name;

    public User(String uId, String uName){
        id = uId;
        name = uName;
    }

    public String getName() { return this.name; }
}
