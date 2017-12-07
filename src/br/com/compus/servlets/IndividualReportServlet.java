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

@WebServlet("/relatorio/individual")
public class IndividualReportServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
  public IndividualReportServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Employee> employees = null;
    try {
      employees = EmployeeDAO.getInstance().getAll();
      request.setAttribute("employees", employees);
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/reports/individual.jsp");
      requestDispatcher.forward(request, response);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/reports/individual_result.jsp");
    requestDispatcher.forward(request, response);
  }

}
