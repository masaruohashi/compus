package br.com.compus.servlets;

import br.com.compus.dao.ClientDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cliente/deletar")
public class ClientDeleteServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("admin_name") == null) {
      response.sendRedirect(request.getContextPath() + "/login");
    } else {
      int id = Integer.parseInt(request.getParameter("id"));
      try {
        if (ClientDAO.getInstance().delete(id)) {
          response.sendRedirect(request.getContextPath() + "/cliente?msg=Cliente deletado com sucesso");
        }
        else {
          response.sendRedirect(request.getContextPath() + "/cliente?msg=Falha ao deletar cliente");
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
