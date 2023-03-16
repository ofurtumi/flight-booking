import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
  public static void main(String[] args) throws SQLException {
    User tumi = new User("0609013170", "Þorvaldur Tumi Baldursson");
    User jonj = new User("3010853549", "Jón Ragnar Jónsson"); // alvöru kennitala hans

    DB db = new DB();
    db.open();
    db.execute("insert into Users (userId, name) values (?, ?);", tumi.getUserData());
    ResultSet rs = db.query("select * from Users;");
    while (rs.next()) {
      System.out.printf("id: %s | user: %s\n", rs.getString(1), rs.getString(2));
    }
    db.close();
  }
}
