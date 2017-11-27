package br.com.compus.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import br.com.compus.models.Employee;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionFactory.class)
public class EmployeeDAOTest {
  private EmployeeDAO dao;
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
    dao = Mockito.spy(new EmployeeDAO());
  }

  @Test
  public void testGetInstance() {
    assertTrue(EmployeeDAO.getInstance() instanceof EmployeeDAO);
  }
  
  @Test
  public void testGetAll() throws SQLException {
    Mockito.when(connection.prepareStatement("SELECT * FROM employee"))
    .thenReturn(statement);
    Mockito.when(statement.executeQuery()).thenReturn(result);
    Mockito.when(result.next()).thenReturn(true).thenReturn(false);
    Mockito.when(result.getInt("id")).thenReturn(1);
    Mockito.when(result.getString("name")).thenReturn("name");
    Mockito.when(result.getString("cpf")).thenReturn("111.111.111-1");
    Mockito.when(result.getString("email")).thenReturn("email@email");
    Mockito.when(result.getString("role")).thenReturn("role");
    List<Employee> employees = dao.getAll();
    assertEquals(1, employees.size());
    assertEquals("name", employees.get(0).getName());
  }
}
