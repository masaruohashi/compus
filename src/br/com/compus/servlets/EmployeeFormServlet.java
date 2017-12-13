package br.com.compus.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.compus.dao.EmployeeDAO;
import br.com.compus.models.Employee;
import br.com.compus.services.EmployeeExistenceValidator;
import br.com.compus.services.DataValidator;


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
    String password = request.getParameter("password");
    String password2 = request.getParameter("password2");

    Map<String, String> employeeValid = DataValidator.validate(name, cpf, email, role, address, phone, password, password2);

    if (employeeValid.get("valid").matches("true")) {
      Employee employee = new Employee();
      employee.setName(name);
      employee.setCpf(cpf);
      employee.setEmail(email);
      employee.setRole(role);
			employee.setAddress(address);
			employee.setPhone(phone);
			if (role.matches("administrador"))
			  employee.setPassword(password);
			else
			  employee.setPassword(null);

      try {
        if(EmployeeExistenceValidator.checkExistingEmployeeForCreate(cpf)) {
          response.sendRedirect(request.getContextPath() + "/funcionario/novo?msg=CPF ja cadastrado&name=" + name +
                                "&email=" + email + "&role=" + role + "&address=" + address + "&phone=" + phone);
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
    else {
      response.sendRedirect(request.getContextPath() + "/funcionario/novo?msg=" + employeeValid.get("msg"));
    }
  }
}
