package br.com.compus.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {
  private Employee mockedEmployee;
  
  @Before
  public void setUp() {
    mockedEmployee = new Employee();
    mockedEmployee.setId(1);
    mockedEmployee.setEmail("john@doe.com");
    mockedEmployee.setCpf("111.111.111-11");
    mockedEmployee.setName("John Doe");
    mockedEmployee.setRole("Admin");
  }

  @Test
  public void testId() {
    assertEquals(1, mockedEmployee.getId());
  }
  
  @Test
  public void testEmail() {
    assertEquals("john@doe.com", mockedEmployee.getEmail());
  }
  
  @Test
  public void testCpf() {
    assertEquals("111.111.111-11", mockedEmployee.getCpf());
  }
  
  @Test 
  public void testName() {
    assertEquals("John Doe", mockedEmployee.getName());
  }
  
  @Test
  public void testRole() {
    assertEquals("Admin", mockedEmployee.getRole());
  }
}
