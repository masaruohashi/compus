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

import br.com.compus.dao.CpuDAO;
import br.com.compus.models.Cpu;

@WebServlet("/processadores")
public class CpusServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public CpusServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Cpu> cpus = null;
    try {
      cpus = CpuDAO.getInstance().getAll();
      request.setAttribute("cpus", cpus);
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/cpus/index.jsp");
      requestDispatcher.forward(request, response);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
