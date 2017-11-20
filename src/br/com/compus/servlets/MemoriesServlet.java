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

import br.com.compus.dao.MemoryDAO;
import br.com.compus.models.Memory;

@WebServlet("/memorias")
public class MemoriesServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public MemoriesServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Memory> memories = null;
    try {
      memories = MemoryDAO.getInstance().getAll();
      request.setAttribute("memories", memories);
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/memories/index.jsp");
      requestDispatcher.forward(request, response);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
