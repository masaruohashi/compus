package br.com.compus.servlets;

import br.com.compus.dao.ClientDAO;
import br.com.compus.models.Client;
import sun.lwawt.macosx.CPlatformEmbeddedFrame;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cliente/novo")
public class ClientFormServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("name");
    String cpf = request.getParameter("cpf");
    String email = request.getParameter("email");
    String address = request.getParameter("address");
    String tel = request.getParameter("tel");

    boolean cliente_valido = true;

    if (name.matches(".*\\d+.*") || name.isEmpty()) {
      cliente_valido = false;
      response.sendRedirect(request.getContextPath() + "/cliente/novo?msg=Insira um nome valido&name=" +
                            name + "&cpf=" + cpf + "&email=" + email + "&address=" + address + "&tel=" + tel);
    }
    else if (cpf.isEmpty() || cpf.length() != "222.222.222-22".length()) {
      cliente_valido = false;
      response.sendRedirect(request.getContextPath() + "/cliente/novo?msg=Insira um cpf valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&address=" + address + "&tel=" + tel);
    }
    else if (!email.matches(".*@.*\\..*")) {
      cliente_valido = false;
      response.sendRedirect(request.getContextPath() + "/cliente/novo?msg=Insira um email valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&address=" + address + "&tel=" + tel);
    }
    else if (address.isEmpty()) {
      cliente_valido = false;
      response.sendRedirect(request.getContextPath() + "/cliente/novo?msg=Insira um endereco valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&address=" + address + "&tel=" + tel);
    }
    else if (tel.isEmpty() || tel.length() != "(22) 2222-2222".length()) {
      cliente_valido = false;
      response.sendRedirect(request.getContextPath() + "/cliente/novo?msg=Insira um tel valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&address=" + address + "&tel=" + tel);
    }

    if (cliente_valido) {
      Client client = new Client();
      client.setName(request.getParameter("name"));
      client.setCpf(request.getParameter("cpf"));
      client.setEmail(request.getParameter("email"));
      client.setAddress(request.getParameter("address"));
      client.setTel(request.getParameter("tel"));

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
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/clients/new.jsp");
    requestDispatcher.forward(request, response);
  }
}
