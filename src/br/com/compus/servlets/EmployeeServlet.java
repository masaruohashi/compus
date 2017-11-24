package br.com.compus.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.compus.dao.EmployeeDAO;
import br.com.compus.models.Employee;

@WebServlet("/funcionario")
public class EmployeeServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public EmployeeServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String id = (request.getParameter("id"));
    try {
      if (id != null) {
        Employee employee = new Employee();
        employee = EmployeeDAO.getInstance().findById(Integer.parseInt(id));
        System.out.println(employee.getName());
        request.setAttribute("employee", employee);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/employees/view.jsp");
        requestDispatcher.forward(request, response);
      }
      else {
      	List<Employee> employees = null;
      	employees = EmployeeDAO.getInstance().getAll();
      	request.setAttribute("employees", employees);
      	RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/employees/index.jsp");
      	requestDispatcher.forward(request, response);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
