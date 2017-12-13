package br.com.compus.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.compus.dao.EmployeeDAO;

@WebServlet("/funcionario/deletar")
public class EmployeeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeDeleteServlet() {
      super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("admin_name") == null) {
      response.sendRedirect(request.getContextPath() + "/login");
    } else {
      int id = Integer.parseInt(request.getParameter("id"));
      try {
        EmployeeDAO.getInstance().delete(id);
        response.sendRedirect(request.getContextPath() + "/funcionario?msg=Usuario deletado com sucesso");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  doGet(request, response);
	}

}
