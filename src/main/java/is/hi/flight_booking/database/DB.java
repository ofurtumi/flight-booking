package is.hi.flight_booking.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DB {
  private Connection conn;
  private final String connectionURL;

  public DB(String URL) {
    connectionURL = String.format("jdbc:sqlite:%s", URL);
  }

  public void open() {
    try {
      if (conn == null) {
        conn = DriverManager.getConnection(connectionURL);
      }
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  public void close() {
    try {
      if (conn != null) {
        conn.close();
      }
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  /**
   * returns a ResultSet containing data from
   * the database corresponding to the given query and values
   * 
   * @param query  an sqlite querystring
   * @param values an array of strings used to add variable data to queries
   * @return ResultSet for the given query and values
   */
  public ResultSet query(String query, String[] values) {
    ResultSet rs = null;
    try {
      PreparedStatement ps = conn.prepareStatement(query);

      for (int v = 0;v < values.length;v++) {
        ps.setString(v + 1, values[v]);
      }

      rs = ps.executeQuery();

    } catch (SQLException e) {
      System.err.println(e);
    }
    return rs;
  }

  /**
   * returns a ResultSet containing data from
   * the database corresponding to the given query and single value string
   * 
   * @param query an sqlite querystring
   * @param value a strings used to add variable data to queries
   * @return ResultSet for the given query and values
   */
  public ResultSet query(String query, String value) {
    ResultSet rs = null;
    try {
      PreparedStatement ps = conn.prepareStatement(query);

      ps.setString(1, value);

      rs = ps.executeQuery();

    } catch (SQLException e) {
      System.err.println(e);
    }
    return rs;
  }

  /**
   * returns a ResultSet containing data from
   * the database corresponding to the given query
   * 
   * @param query an sqlite querystring
   * @return ResultSet for the given query
   */
  public ResultSet query(String query) {
    ResultSet rs = null;
    try {
      Statement s = conn.createStatement();

      rs = s.executeQuery(query);
    } catch (SQLException e) {
      System.err.println(e);
    }
    return rs;
  }

  /**
   * executes a given sqlite query on the database using external values given as
   * a parametr, does not return anything
   * 
   * @param query  an sqlite querystring
   * @param values an array of strings used to add variable data to query
   */
  public void execute(String query, String[] values) {
    try {
      PreparedStatement ps = conn.prepareStatement(query);
      for (int i = 0;i < values.length;i++) {
        ps.setString(i + 1, values[i]);
      }
      ps.executeUpdate();
      ps.close();
    } catch (SQLException e) {
      System.err.println(e);
    }
  }

  /**
   * executes a given sqlite query on the database, does not return anything
   * 
   * @param query a sqlite querystring
   */
  public void execute(String query) {
    try {
      Statement s = conn.createStatement();
      s.executeUpdate(query);
      s.close();
    } catch (SQLException e) {
      System.err.println(e);
    }
  }

  /*
   * public static void main(String[] args) throws Exception {
   * String[] val = new String[1];
   * val[0] = "1";
   * open();
   * ResultSet rs = query("SELECT * FROM User where userID = ?;", val);
   * System.out.printf("%s", rs.getString(2));
   * close();
   * }
   */
}
