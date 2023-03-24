import java.sql.ResultSet;

public class User {
  private String userID;
  private String userName;

  public User(String userID, String userName) {
    this.userID = userID;
    this.userName = userName;
  }

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String[] getUserData() {
    String[] data = {getUserID(), getUserName()};
    return data;
  }

  /*
   * public static void main(String[] args) throws Exception {
   * DB database = new DB();
   * 
   * // dæmi um að kalla á gagnagrunninn með auka parametra
   * database.open();
   * ResultSet rs1 = database.query("SELECT * FROM Users where userID = ?;",
   * args);
   * while (rs1.next()) {
   * System.out.printf("User #%d: %s\n", rs1.getInt(1), rs1.getString(2));
   * }
   * database.close();
   * 
   * // ! dæmi um að kalla á gagnagrunninn á auka parametra
   * database.open();
   * ResultSet rs2 = database.query("SELECT * FROM USERS");
   * while (rs2.next()) {
   * System.out.printf("id: %d, name: %s\n", rs2.getInt(1), rs2.getString(2));
   * }
   * database.close();
   * }
   */
}
