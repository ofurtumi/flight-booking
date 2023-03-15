import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DB {
  private static String connectionURL = "jdbc:sqlite:db/flugdb.db";
  private static Connection conn;

  public static void open() {
    try {
      conn = DriverManager.getConnection(connectionURL);
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  public static void close() {
    try {
      conn.close();
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  public static ResultSet query(String query, String[] values) {
    ResultSet rs = null;
    try {
      PreparedStatement ps = conn.prepareStatement(query);
      
      for (int v = 0; v < values.length; v++) {
        ps.setString(v+1, values[v]);
      }

      rs = ps.executeQuery();

    } catch (Exception e) {
      System.err.println(e);
    }
    return rs;
  }  

  /*
  public static void main(String[] args) throws Exception {
    String[] val = new String[1];
    val[0] = "1";
    open();
    ResultSet rs = query("SELECT * FROM User where userID = ?;", val);
    System.out.printf("%s", rs.getString(2));
    close();
  }*/
}
