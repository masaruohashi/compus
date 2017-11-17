package br.com.compus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.compus.jdbc.ConnectionFactory;
import br.com.compus.models.Employee;

public class EmployeeDAO {
  private Connection connection;

  public EmployeeDAO() {
    connection = ConnectionFactory.getConnection();
  }

  public static EmployeeDAO getInstance() {
    return new EmployeeDAO();
  }

  public List<Employee> getAll() throws SQLException {
    List<Employee> employees = new ArrayList<Employee>();
    try {
      String sql = "SELECT * FROM employee";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        Employee employee = new Employee();
        employee.setId(result.getInt("id"));
        employee.setName(result.getString("name"));
        employee.setCpf(result.getString("cpf"));
        employee.setEmail(result.getString("email"));
        employee.setRole(result.getString("role"));
        employees.add(employee);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return employees;
  }

  public Employee findById(int id) throws SQLException {
    Employee employee = null;
    try {
      String sql = "SELECT * FROM employee WHERE id = ?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        employee = new Employee();
        employee.setName(result.getString("name"));
        employee.setEmail(result.getString("email"));
        employee.setCpf(result.getString("cpf"));
        employee.setRole(result.getString("role"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return employee;
  }

  public boolean delete(int id) throws SQLException {
    try {
      String sql = "DELETE * FROM employee WHERE id = ?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      statement.execute();
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean create(Employee employee) throws SQLException {
    String sql = "INSERT INTO employee (name, email, cpf, role)" +
                 "VALUES (?, ?, ?, ?)";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setString(1, employee.getName());
      statement.setString(2, employee.getEmail());
      statement.setString(3, employee.getCpf());
      statement.setString(4, employee.getRole());
      statement.execute();
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  public boolean edit (int id, Employee employee) throws SQLException {
	  String sql = "UPDATE Employee" + 
			  	   "SET name = ?, email = ?, cpf = ?, role = ?" + 
			  	   "WHRE  id = ?";
	  try {
		  PreparedStatement statement = this.connection.prepareStatement(sql);
		  statement.setString(1, employee.getName());
		  statement.setString(2, employee.getEmail());
		  statement.setString(3, employee.getCpf());
		  statement.setString(4 , employee.getRole());
		  statement.setInt(5, id);
		  statement.execute();
		  statement.close();
		  return true;
	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
	  return false;
  }
}
