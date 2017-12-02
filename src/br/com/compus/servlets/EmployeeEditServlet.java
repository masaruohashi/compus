package br.com.compus.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.compus.dao.EmployeeDAO;
import br.com.compus.models.Employee;

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(request.getParameter("id")));
	    employee.setName(request.getParameter("name"));
	    employee.setCpf(request.getParameter("cpf"));
	    employee.setEmail(request.getParameter("email"));
	    employee.setRole(request.getParameter("role"));
	    try {
	    	if (EmployeeDAO.getInstance().edit(employee)) {
	    		response.sendRedirect(request.getContextPath() + "/funcionario?msg=Usuario editado com sucesso");
	    	}
	    	else {
	    		doGet(request, response);
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	}

}
