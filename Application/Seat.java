public class Seat {
    private String id;
    private String flightId;
    private boolean reserved;

    public Seat(String sId, String fId,Boolean res){
        id = sId;
        flightId = fId;
        reserved = res;
    }
}
