package br.com.compus.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.compus.dao.CpuDAO;
import br.com.compus.dao.HdDAO;
import br.com.compus.dao.MemoryDAO;
import br.com.compus.dao.MotherboardDAO;
import br.com.compus.models.Cpu;
import br.com.compus.models.Hd;
import br.com.compus.models.Memory;
import br.com.compus.models.Motherboard;

@WebServlet("/computadores")
public class ComputerAssembliesServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ComputerAssembliesServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Cpu> cpus = null;
    List<Motherboard> motherboards = null;
    List<Memory> memories = null;
    List<Hd> hds = null;
    try {
      cpus = CpuDAO.getInstance().getAll();
      motherboards = MotherboardDAO.getInstance().getAll();
      memories = MemoryDAO.getInstance().getAll();
      hds = HdDAO.getInstance().getAll();
      request.setAttribute("cpus", cpus);
      request.setAttribute("motherboards", motherboards);
      request.setAttribute("memories", memories);
      request.setAttribute("hds", hds);
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/computers/new.jsp");
      requestDispatcher.forward(request, response);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
