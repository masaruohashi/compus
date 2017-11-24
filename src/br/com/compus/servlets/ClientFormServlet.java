package br.com.compus.servlets;

import br.com.compus.dao.ClientDAO;
import br.com.compus.models.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ClientFormServlet")
public class ClientFormServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Client client = new Client();
    client.setName(request.getParameter("name"));
    client.setCpf(request.getParameter("cpf"));
    client.setEmail(request.getParameter("email"));
    client.setAddress(request.getParameter("address"));
    client.setTel(request.getParameter("tel"));
    try {
      if(ClientDAO.getInstance().create(client)) {
        response.sendRedirect(request.getContextPath() + "/cliente?msg=Cliente criado com sucesso");
      }
      else {
        doGet(request, response);
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/clients/new.jsp");
    requestDispatcher.forward(request, response);
  }
}
