package br.com.compus.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/reports/general_result.jsp");
    requestDispatcher.forward(request, response);
    doGet(request, response);
  }

}
