package br.com.compus.servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import br.com.compus.dao.CpuDAO;
import br.com.compus.dao.HdDAO;
import br.com.compus.dao.MemoryDAO;
import br.com.compus.dao.MotherboardDAO;
import br.com.compus.models.Computer;
import br.com.compus.models.Cpu;
import br.com.compus.models.Hd;
import br.com.compus.models.Memory;
import br.com.compus.models.Motherboard;
import br.com.compus.services.ComputerValidator;

@WebServlet("/computadores")
public class ComputerAssembliesServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ComputerAssembliesServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("employee_cpf") == null || session.getAttribute("client_cpf") == null) {
      response.sendRedirect(request.getContextPath() + "/identificacao");
    } else {
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
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("employee_cpf") == null || session.getAttribute("client_cpf") == null) {
      response.sendRedirect(request.getContextPath() + "/identificacao");
    } else {
      try {
        Motherboard motherboard = MotherboardDAO.getInstance().findById(Integer.parseInt(request.getParameter("motherboard")));
        Cpu cpu = CpuDAO.getInstance().findById(Integer.parseInt(request.getParameter("cpu")));
        Hd hd = HdDAO.getInstance().findById(Integer.parseInt(request.getParameter("hd")));
        Memory memory = MemoryDAO.getInstance().findById(Integer.parseInt(request.getParameter("memory")));
        int memoryQuantity = Integer.parseInt(request.getParameter("memory_quantity"));
        int hdQuantity = Integer.parseInt(request.getParameter("hd_quantity"));
        Computer computer = new Computer(motherboard, cpu, hd, memory, hdQuantity, memoryQuantity);
        Map<String, String> data = new HashMap<String, String>();
        ComputerValidator computerValidator = new ComputerValidator(computer);
        if(computerValidator.validate()) {
          Cookie computerCookie = getCookie(request, "computers");
          String computerCookieValue = URLDecoder.decode(computerCookie.getValue(), "utf8");
          if(!computerCookieValue.isEmpty()) {
            computerCookieValue = computerCookieValue.concat(";");
          }
          computerCookieValue = computerCookieValue.concat(motherboard.getId() + "|" + cpu.getId() + "|" + hd.getId() + "|" +
            hdQuantity + "|" + memory.getId() + "|" + memoryQuantity);
          computerCookie.setValue(URLEncoder.encode(computerCookieValue, "utf8"));
          response.addCookie(computerCookie);
          data.put("message", "Computador adicionado com sucesso!");
        } else {
          data.put("message", "As peças não são compatíveis!");
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject jsonData = new JSONObject(data);
        response.getWriter().write(jsonData.toString());
      }
      catch (NumberFormatException e) {
        e.printStackTrace();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private Cookie getCookie(HttpServletRequest request, String name) {
    if(request.getCookies() != null) {
      for(Cookie cookie : request.getCookies()) {
        if(cookie.getName().equals(name)) {
          return cookie;
        }
      }
    }
    return new Cookie(name, "");
  }
}
