package br.com.compus.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.compus.controller.EmployeeController;
import br.com.compus.dao.EmployeeDAO;
import br.com.compus.models.Employee;


@WebServlet("/funcionario/novo")
public class EmployeeFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

  public EmployeeFormServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/employees/new.jsp");
    requestDispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name  = request.getParameter("name");
    String cpf   = request.getParameter("cpf");
    String email = request.getParameter("email");
    String role  = request.getParameter("role");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

    boolean employee_valid = true;

    if (name.matches(".*\\d+.*") || name.isEmpty()) {
      employee_valid = false;
      response.sendRedirect(request.getContextPath() + "/funcionario/novo?msg=Insira um nome valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&role=" + role + "&address=" + address + "&phone=" + phone);
    }
    else if (cpf.isEmpty() || cpf.length() != "222.222.222-22".length()) {
      employee_valid = false;
      response.sendRedirect(request.getContextPath() + "/funcionario/novo?msg=Insira um cpf valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&role=" + role + "&address=" + address + "&phone=" + phone);
    }
    else if (!email.matches("..*@.*\\...*.")) {
      employee_valid = false;
      response.sendRedirect(request.getContextPath() + "/funcionario/novo?msg=Insira um e-mail valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&role=" + role + "&address=" + address + "&phone=" + phone);
    }

    if (employee_valid) {
      Employee employee = new Employee();
      employee.setName(name);
      employee.setCpf(cpf);
      employee.setEmail(email);
      employee.setRole(role);
			employee.setAddress(address);
			employee.setPhone(phone);
      try {
        if(EmployeeController.checkExistingEmployee(cpf)) {
          response.sendRedirect(request.getContextPath() + "/funcionario/novo?msg=CPF ja cadastrado");
        }
        else {
          if (EmployeeDAO.getInstance().create(employee)) {
            
            response.sendRedirect(request.getContextPath() + "/funcionario?msg=Usuario criado com sucesso");
          } else {
            doGet(request, response);
          }
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
