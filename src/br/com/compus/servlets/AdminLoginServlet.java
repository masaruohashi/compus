package br.com.compus.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.compus.dao.EmployeeDAO;
import br.com.compus.models.Employee;

@WebServlet("/login")
public class AdminLoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public AdminLoginServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("admin_cpf") == null || session.getAttribute("admin_password") == null) {
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/admin/login.jsp");
      requestDispatcher.forward(request, response);
    } else {
      response.sendRedirect(request.getContextPath() + "/funcionario");
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String adminCpf = request.getParameter("admin_cpf");
    String adminPassword = request.getParameter("admin_password");
    try {
      Employee employee = EmployeeDAO.getInstance().findByCpf(adminCpf);
      if(employee != null && employee.getRole().matches("administrador") && employee.getPassword().matches(adminPassword)) {
        HttpSession session = request.getSession();
        session.setAttribute("admin_name", employee.getName());
        for(Cookie cookie: request.getCookies()) {
          cookie.setMaxAge(0);
          response.addCookie(cookie);
        }
        response.sendRedirect(request.getContextPath() + "/funcionario");
      } else {
        request.setAttribute("message", "Administrador não cadastrado ou senha inválida.");
        doGet(request, response);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
