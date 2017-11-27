package br.com.compus.jdbc;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionFactory.class)
public class ConnectionFactoryTest {
  private Connection connection;
  @Before
  public void setUp() {
    connection = Mockito.mock(Connection.class);
  }

  @Test
  public void testConnection() throws SQLException, ClassNotFoundException {
    PowerMockito.mockStatic(ConnectionFactory.class);
    Mockito.when(ConnectionFactory.getConnection()).thenReturn(connection);
    assertEquals(connection, ConnectionFactory.getConnection());
  }
}
