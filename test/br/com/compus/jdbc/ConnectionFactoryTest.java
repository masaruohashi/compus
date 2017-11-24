package br.com.compus.jdbc;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import org.junit.Test;

public class ConnectionFactoryTest {

  @Test
  public void testConnection() {
    Connection connection = ConnectionFactory.getConnection();
    assertNotNull(connection);
  }

}
