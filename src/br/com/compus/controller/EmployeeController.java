package br.com.compus.controller;

import java.sql.SQLException;
import java.util.List;

import br.com.compus.dao.EmployeeDAO;
import br.com.compus.models.Employee;

public class EmployeeController {
  
  public static boolean checkExistingEmployee(String cpf) {
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
}
