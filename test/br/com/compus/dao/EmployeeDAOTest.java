package br.com.compus.dao;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.compus.models.Employee;

public class EmployeeDAOTest {
  private Employee mockedEmployee = new Employee();
  
  @Before
  public void setUp() {
    mockedEmployee.setName("Jonh Doe");
    mockedEmployee.setCpf("111.111.111-11");
    mockedEmployee.setEmail("john@doe.com");
    mockedEmployee.setRole("Administrador");
  }

  @Test
  public void testGetInstance() {
    assertTrue(EmployeeDAO.getInstance() instanceof EmployeeDAO);
  }
  
  @Test
  public void testCreate() {
    
    try {
    assertTrue(EmployeeDAO.getInstance().create(mockedEmployee));
    } catch (SQLException e) {
    e.printStackTrace();
    }
  }
  
  @Test
  public void testGetAll() {
    List<Employee> employees= null;
    try {
      employees = EmployeeDAO.getInstance().getAll();
      assertTrue(employees.size() > 0);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testDelete() {
    try {
    assertTrue(EmployeeDAO.getInstance().delete(1));
    } catch (SQLException e) {
    e.printStackTrace();
    }
  }
 

}
