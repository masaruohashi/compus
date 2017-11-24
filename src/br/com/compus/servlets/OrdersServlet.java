package br.com.compus.servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javax.servlet.http.HttpUtils;

import br.com.compus.dao.CpuDAO;
import br.com.compus.models.Cpu;
import br.com.compus.models.Product;

@WebServlet("/carrinho")
public class OrdersServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public OrdersServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Cookie[] cookies = request.getCookies();
    Map<String, Cookie> cookieMap = new HashMap<>();
    for(Cookie cookie : cookies) {
      cookieMap.put(cookie.getName(), cookie);
    }
    List<Product> products = null;
    try {
      getCpusByCookie(cookieMap.get("cpu_ids"));
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/orders/index.jsp");
      requestDispatcher.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  private List<Cpu> getCpusByCookie(Cookie cpusCookie) {
    List<Cpu> cpus = new ArrayList<Cpu>();
    try {
      String[] cpuIds = URLDecoder.decode(cpusCookie.getValue(), "utf8").split(";");
      for(String id : cpuIds) {
        cpus.add(CpuDAO.getInstance().findById(Integer.parseInt(id)));
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
    return cpus;
  }
}
