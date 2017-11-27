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
import br.com.compus.models.Memory;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionFactory.class)
public class MemoryDAOTest {
  private MemoryDAO dao;
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
    dao = new MemoryDAO();
  }

  @Test
  public void testGetAllWithValidValues() throws SQLException {
    Mockito.when(connection.prepareStatement("SELECT * FROM memory"))
      .thenReturn(statement);
    Mockito.when(statement.executeQuery()).thenReturn(result);
    Mockito.when(result.next()).thenReturn(true).thenReturn(false);
    Mockito.when(result.getInt("id")).thenReturn(1);
    Mockito.when(result.getString("name")).thenReturn("name");
    Mockito.when(result.getDouble("price")).thenReturn(1.5);
    Mockito.when(result.getString("capacity")).thenReturn("1TB");
    Mockito.when(result.getString("type")).thenReturn("DDR3");
    List<Memory> memories = dao.getAll();
    assertEquals(1, memories.size());
    assertEquals("name", memories.get(0).getName());
  }

  @Test
  public void testGetAllWithoutAnyValue() throws SQLException {
    Mockito.when(connection.prepareStatement("SELECT * FROM memory"))
      .thenReturn(statement);
    Mockito.when(statement.executeQuery()).thenReturn(result);
    List<Memory> memories = dao.getAll();
    assertEquals(0, memories.size());
  }

  @Test
  public void testFindByIdWithValidID() throws SQLException {
    Mockito.when(connection.prepareStatement("SELECT * FROM memory WHERE id=?"))
      .thenReturn(statement);
    Mockito.when(statement.executeQuery()).thenReturn(result);
    Mockito.when(result.next()).thenReturn(true);
    Mockito.when(result.getInt("id")).thenReturn(1);
    Mockito.when(result.getString("name")).thenReturn("name");
    Mockito.when(result.getDouble("price")).thenReturn(1.5);
    Mockito.when(result.getString("capacity")).thenReturn("1TB");
    Mockito.when(result.getString("type")).thenReturn("DDR3");
    Memory memory = dao.findById(1);
    assertEquals(1, memory.getId());
    assertEquals("name", memory.getName());
    assertEquals(1.5, memory.getPrice(), 10E-2);
    assertEquals("1TB", memory.getCapacity());
    assertEquals("DDR3", memory.getType());
  }
}
