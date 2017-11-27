package br.com.compus.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.compus.jdbc.ConnectionFactory;
import br.com.compus.models.Hd;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionFactory.class)
public class HdDAOTest {
  private HdDAO dao;
  private Connection connection;
  private PreparedStatement statement;
  private ResultSet result;

  @Before
  public void setUp() {
    connection = Mockito.mock(Connection.class);
    statement = Mockito.mock(PreparedStatement.class);
    result = Mockito.mock(ResultSet.class);
    PowerMockito.mockStatic(ConnectionFactory.class);
    Mockito.when(ConnectionFactory.getConnection()).thenReturn(connection);
    dao = new HdDAO();
  }

  @Test
  public void testGetAllWithValidValues() throws SQLException {
    Mockito.when(connection.prepareStatement("SELECT * FROM hd"))
      .thenReturn(statement);
    Mockito.when(statement.executeQuery()).thenReturn(result);
    Mockito.when(result.next()).thenReturn(true).thenReturn(false);
    Mockito.when(result.getInt("id")).thenReturn(1);
    Mockito.when(result.getString("name")).thenReturn("name");
    Mockito.when(result.getDouble("price")).thenReturn(1.5);
    Mockito.when(result.getString("capacity")).thenReturn("1TB");
    List<Hd> hds = dao.getAll();
    assertEquals(1, hds.size());
    assertEquals("name", hds.get(0).getName());
  }

  @Test
  public void testGetAllWithoutAnyValue() throws SQLException {
    Mockito.when(connection.prepareStatement("SELECT * FROM hd"))
      .thenReturn(statement);
    Mockito.when(statement.executeQuery()).thenReturn(result);
    List<Hd> hds = dao.getAll();
    assertEquals(0, hds.size());
  }

  @Test
  public void testFindByIdWithValidID() throws SQLException {
    Mockito.when(connection.prepareStatement("SELECT * FROM hd WHERE id=?"))
      .thenReturn(statement);
    Mockito.when(statement.executeQuery()).thenReturn(result);
    Mockito.when(result.next()).thenReturn(true);
    Mockito.when(result.getInt("id")).thenReturn(1);
    Mockito.when(result.getString("name")).thenReturn("name");
    Mockito.when(result.getDouble("price")).thenReturn(1.5);
    Mockito.when(result.getString("capacity")).thenReturn("1TB");
    Hd hd = dao.findById(1);
    assertEquals(1, hd.getId());
    assertEquals("name", hd.getName());
    assertEquals(1.5, hd.getPrice(), 10E-2);
    assertEquals("1TB", hd.getCapacity());
  }
}
