package br.com.compus.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
  public static Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName("org.mariadb.jdbc.Driver");
      connection = DriverManager.getConnection("jdbc:mariadb://143.107.102.5:3306/t2g5", "t2g5", "ttYMT5++");
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return connection;
  }
}
