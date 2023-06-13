package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static Connection connection;

  public static Connection getConnection() throws SQLException {
    if (connection == null || connection.isClosed()) {
      String url = "jdbc:postgresql://containers-us-west-103.railway.app:6478/sistemaenade";
      String user = "postgres";
      String password = "3fpFq9V8JdWtbphqLbcS";
      connection = DriverManager.getConnection(url, user, password);
    }
    return connection;
  }
}
