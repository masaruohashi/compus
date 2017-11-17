package br.com.compus.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.compus.dao.EmployeeDAO;
import br.com.compus.models.Employee;


@WebServlet("/funcionario/novo")
public class EmployeeFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

  public EmployeeFormServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/employees/new.jsp");
    requestDispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Employee employee = new Employee();
    employee.setName(request.getParameter("name"));
    employee.setCpf(request.getParameter("cpf"));
    employee.setEmail(request.getParameter("email"));
    employee.setRole(request.getParameter("role"));
    try {
      if(EmployeeDAO.getInstance().create(employee)) {
        response.sendRedirect(request.getContextPath() + "/funcionario?msg=Usuario criado com sucesso");
      }
      else {
        doGet(request, response);
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
