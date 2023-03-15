import java.sql.ResultSet;

public class User {
  public static void main(String[] args) throws Exception {
    DB database = new DB();

    database.open();
    ResultSet rs = database.query("SELECT * FROM Users where userID = ?;", args);
    while (rs.next()) {
      System.out.printf("User #%d: %s", rs.getInt(1), rs.getString(2));
    }
    database.close();
 
  }
}
