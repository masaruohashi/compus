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

@WebServlet("/funcionario/editar")
public class EmployeeEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeEditServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = new Employee();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			employee = EmployeeDAO.getInstance().findById(id);
			request.setAttribute("employee", employee);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/employees/edit.jsp");
			requestDispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String cpf = request.getParameter("cpf");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");

		Map<String, String> employeeValid = DataValidator.validate(name, cpf, email, role, address, phone, password);

		if(employeeValid.get("valid").matches("true")) {
			Employee employee = new Employee();
			employee.setId(id);
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
				if(EmployeeExistenceValidator.checkExistingEmployeeForEdit(cpf, id)) {
					response.sendRedirect(request.getContextPath() + "/funcionario/editar?id=" + id + "&msg=CPF ja cadastrado");
				}
				else {
					if (EmployeeDAO.getInstance().edit(employee)) {
						response.sendRedirect(request.getContextPath() + "/funcionario?msg=Usuario editado com sucesso");
					}
					else {
						response.sendRedirect(request.getContextPath() + "/funcionario/editar?msg=Falha ao editar funcionario");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			response.sendRedirect(request.getContextPath() + "/funcionario/editar?id=" + id + "&msg=" + employeeValid.get("msg"));
		}
	}

}
