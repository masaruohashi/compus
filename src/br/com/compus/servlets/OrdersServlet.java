package br.com.compus.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import br.com.compus.dao.CpuDAO;
import br.com.compus.dao.HdDAO;
import br.com.compus.dao.MemoryDAO;
import br.com.compus.dao.MotherboardDAO;
import br.com.compus.models.Cpu;
import br.com.compus.models.Hd;
import br.com.compus.models.Memory;
import br.com.compus.models.Motherboard;
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
    List<Product> products = new ArrayList<Product>();
    try {
      products.addAll(getCpusByCookie(cookieMap.get("cpu_ids")));
      products.addAll(getMotherboardsByCookie(cookieMap.get("motherboard_ids")));
      products.addAll(getMemoriesByCookie(cookieMap.get("memory_ids")));
      products.addAll(getHdsByCookie(cookieMap.get("hd_ids")));
      request.setAttribute("products", products);
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
    } catch (SQLException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return cpus;
  }

  private List<Motherboard> getMotherboardsByCookie(Cookie motherboardsCookie) {
    List<Motherboard> motherboards = new ArrayList<Motherboard>();
    try {
      String[] motherboardIds = URLDecoder.decode(motherboardsCookie.getValue(), "utf8").split(";");
      for(String id : motherboardIds) {
        motherboards.add(MotherboardDAO.getInstance().findById(Integer.parseInt(id)));
      }
    } catch (SQLException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return motherboards;
  }

  private List<Memory> getMemoriesByCookie(Cookie memoriesCookie) {
    List<Memory> memories = new ArrayList<Memory>();
    try {
      String[] memoriesIds = URLDecoder.decode(memoriesCookie.getValue(), "utf8").split(";");
      for(String id : memoriesIds) {
        memories.add(MemoryDAO.getInstance().findById(Integer.parseInt(id)));
      }
    } catch (SQLException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return memories;
  }

  private List<Hd> getHdsByCookie(Cookie hdsCookie) {
    List<Hd> hds = new ArrayList<Hd>();
    try {
      String[] hdsIds = URLDecoder.decode(hdsCookie.getValue(), "utf8").split(";");
      for(String id : hdsIds) {
        hds.add(HdDAO.getInstance().findById(Integer.parseInt(id)));
      }
    } catch (SQLException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return hds;
  }
}
