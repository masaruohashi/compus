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

import br.com.compus.dao.ReportDAO;
import br.com.compus.models.Report;

@WebServlet("/relatorio/geral")
public class GeneralReportSerlvlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

    public GeneralReportSerlvlet() {
      super();
    }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/reports/general.jsp");
    requestDispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String date = request.getParameter("date");
    String[] splittedDate = date.split("/");
    int month = Integer.parseInt(splittedDate[0]) + 1;
    int year = Integer.parseInt(splittedDate[1]);
    try {
      List<Report> generalReports = ReportDAO.getInstance().getGeneralReport(month, year);
      if(!generalReports.isEmpty()) {
        request.setAttribute("generalReports", generalReports);
        request.setAttribute("date", month + "/" + year);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/reports/general_result.jsp");
        requestDispatcher.forward(request, response);        
      }
      else {
        response.sendRedirect(request.getContextPath() + "/relatorio/geral?msg=Mes especificado nao contem vendas registradas");
      }
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }

}
