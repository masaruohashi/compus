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
import br.com.compus.dao.ReportDAO;
import br.com.compus.models.Employee;
import br.com.compus.models.Report;

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
    int employeeId = Integer.parseInt(request.getParameter("employee"));
    try {
      List<Report> generalReports = ReportDAO.getInstance().getIndividualReport(employeeId);
      if(!generalReports.isEmpty()) {
        Employee employee = EmployeeDAO.getInstance().findById(employeeId);
        request.setAttribute("individualReports", generalReports);
        request.setAttribute("employeeName", employee.getName());
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/reports/individual_result.jsp");
        requestDispatcher.forward(request, response);        
      }
      else {
        response.sendRedirect(request.getContextPath() + "/relatorio/individual?msg=Vendedor especificado nao contem vendas registradas");
      }
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }

}
