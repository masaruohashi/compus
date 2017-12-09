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

import br.com.compus.dao.ClientDAO;
import br.com.compus.dao.EmployeeDAO;
import br.com.compus.models.Client;
import br.com.compus.models.Employee;

@WebServlet("/identificacao")
public class OrderInformationsServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public OrderInformationsServlet() {
      super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("employee_cpf") == null || session.getAttribute("client_cpf") == null) {
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/order_infos/new.jsp");
      requestDispatcher.forward(request, response);
    } else {
      response.sendRedirect(request.getContextPath() + "/processadores");
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String employeeCpf = request.getParameter("employee_cpf");
    String clientCpf = request.getParameter("client_cpf");
    try {
      Employee employee = EmployeeDAO.getInstance().findByCpf(employeeCpf);
      Client client = ClientDAO.getInstance().findByCpf(clientCpf);
      if(employee != null && client != null) {
        HttpSession session = request.getSession();
        session.setAttribute("employee_cpf", employeeCpf);
        session.setAttribute("client_cpf", clientCpf);
        for(Cookie cookie : request.getCookies()) {
          cookie.setMaxAge(0);
          response.addCookie(cookie);
        }
        response.sendRedirect(request.getContextPath() + "/processadores");
      } else {
        request.setAttribute("message", "Vendedor ou Cliente não cadastrado.");
        doGet(request, response);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
