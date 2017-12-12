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
import javax.servlet.http.HttpSession;

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
	  HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("admin_name") == null) {
      response.sendRedirect(request.getContextPath() + "/identificacao");
    } else {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("admin_name") == null) {
      response.sendRedirect(request.getContextPath() + "/login");
    } else {
      int id = Integer.parseInt(request.getParameter("id"));
      String name = request.getParameter("name");
      String cpf = request.getParameter("cpf");
      String email = request.getParameter("email");
      String role = request.getParameter("role");
      String address = request.getParameter("address");
      String phone = request.getParameter("phone");
      
      Map<String, String> client_valid = DataValidator.validate(name, cpf, email, address, phone);
      if(client_valid.get("valid").matches("true")) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setCpf(cpf);
        employee.setEmail(email);
        employee.setRole(role);
        employee.setAddress(address);
        employee.setPhone(phone);
        try {
          if(EmployeeExistenceValidator.checkExistingEmployeeForEdit(request.getParameter("cpf"), Integer.parseInt(request.getParameter("id")))) {
            response.sendRedirect(request.getContextPath() + "/funcionario/editar?id=" + id + "&msg=CPF ja cadastrado");
          }
          else {
            if (EmployeeDAO.getInstance().edit(employee)) {
              response.sendRedirect(request.getContextPath() + "/funcionario?msg=Usuario editado com sucesso");
            }
            else {
              doGet(request, response);
            }	        
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      else {
        response.sendRedirect(request.getContextPath() + "/funcionario/editar?id=" + id + "&msg" + client_valid.get("msg"));
      }
    }
	}

}
