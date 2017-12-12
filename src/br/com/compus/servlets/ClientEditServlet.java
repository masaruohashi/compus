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

@WebServlet("/cliente/editar")
public class ClientEditServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Client client = new Client();

    client.setId((Integer.parseInt(request.getParameter("id"))));
    client.setAddress(request.getParameter("address"));
    client.setPhone(request.getParameter("phone"));
    client.setCpf(request.getParameter("cpf"));
    client.setEmail(request.getParameter("email"));
    client.setName(request.getParameter("name"));

    try {
      if (ClientDAO.getInstance().edit(client)) {
        response.sendRedirect(request.getContextPath() + "/cliente?msg=Cliente editado com sucesso");
      }
      else {
        response.sendRedirect(request.getContextPath() + "/cliente/editar?msg=Falha ao editar cliente");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Client client = new Client();

    try {
      client = ClientDAO.getInstance().findById(Integer.parseInt(request.getParameter("id")));
      request.setAttribute("client", client);
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/clients/edit.jsp");
      requestDispatcher.forward(request, response);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
