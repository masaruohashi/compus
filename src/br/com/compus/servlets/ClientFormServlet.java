package br.com.compus.servlets;

import br.com.compus.dao.ClientDAO;
import br.com.compus.models.Client;
import br.com.compus.controller.ValidateInputData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/cliente/novo")
public class ClientFormServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ValidateInputData validation = new ValidateInputData();

    String name = request.getParameter("name");
    String cpf = request.getParameter("cpf");
    String email = request.getParameter("email");
    String address = request.getParameter("address");
    String phone = request.getParameter("phone");

    Map<String, String> client_valid = validation.validateClient(name, cpf, email, address, phone);

    if (client_valid.get("valid").matches("true")) {
      Client client = new Client();
      client.setName(name);
      client.setCpf(cpf);
      client.setEmail(email);
      client.setAddress(address);
      client.setPhone(phone);

      try {
        if (ClientDAO.getInstance().create(client)) {
          response.sendRedirect(request.getContextPath() + "/cliente?msg=Cliente criado com sucesso");
        } else {
          doGet(request, response);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    else {
      response.sendRedirect(request.getContextPath() + client_valid.get("url"));
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/clients/new.jsp");
    requestDispatcher.forward(request, response);
  }
}
