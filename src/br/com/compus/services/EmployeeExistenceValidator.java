package br.com.compus.services;

import java.sql.SQLException;
import java.util.List;

import br.com.compus.dao.EmployeeDAO;
import br.com.compus.models.Employee;

public class EmployeeExistenceValidator {
  
  public static boolean checkExistingEmployeeForCreate(String cpf) {
    try {
      List<Employee> employees = EmployeeDAO.getInstance().getAll();
      for (Employee employee: employees) {
        if(employee.getCpf().equals(cpf)) {
          return true;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  public static boolean checkExistingEmployeeForEdit(String cpf, int id) {
    try {
      List<Employee> employees = EmployeeDAO.getInstance().getAll();
      for (Employee employee: employees) {
        if(employee.getCpf().equals(cpf) && employee.getId() != id) {
          return true;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
