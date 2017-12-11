package br.com.compus.servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.compus.dao.CpuDAO;
import br.com.compus.dao.HdDAO;
import br.com.compus.dao.MemoryDAO;
import br.com.compus.dao.MotherboardDAO;
import br.com.compus.models.Computer;
import br.com.compus.models.Cpu;
import br.com.compus.models.Hd;
import br.com.compus.models.Memory;
import br.com.compus.models.Motherboard;
import br.com.compus.models.Order;
import br.com.compus.models.Product;
import br.com.compus.services.CookieHandler;
import br.com.compus.services.OrderPriceCalculator;

@WebServlet("/carrinho")
public class OrdersServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public OrdersServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("employee_cpf") == null || session.getAttribute("client_cpf") == null) {
      response.sendRedirect(request.getContextPath() + "/identificacao");
    } else {
      Map<String, Cookie> cookieMap = CookieHandler.getCookieMap(request.getCookies());
      List<Motherboard> motherboards = new ArrayList<Motherboard>();
      List<Cpu> cpus = new ArrayList<Cpu>();
      List<Hd> hds = new ArrayList<Hd>();
      List<Memory> memories = new ArrayList<Memory>();
      List<Computer> computers = new ArrayList<Computer>();
      motherboards.addAll(CookieHandler.getMotherboardsByCookie(cookieMap.get("motherboard_ids")));
      cpus.addAll(CookieHandler.getCpusByCookie(cookieMap.get("cpu_ids")));
      hds.addAll(CookieHandler.getHdsByCookie(cookieMap.get("hd_ids")));
      memories.addAll(CookieHandler.getMemoriesByCookie(cookieMap.get("memory_ids")));
      computers.addAll(CookieHandler.getComputersByCookie(cookieMap.get("computers")));
      request.setAttribute("motherboards", motherboards);
      request.setAttribute("cpus", cpus);
      request.setAttribute("hds", hds);
      request.setAttribute("memories", memories);
      request.setAttribute("computers", computers);
      RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/orders/index.jsp");
      requestDispatcher.forward(request, response);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("employee_cpf") == null || session.getAttribute("client_cpf") == null) {
      response.sendRedirect(request.getContextPath() + "/identificacao");
    } else {
      Map<String, String[]> parameters = request.getParameterMap();
      Order order = new Order();
      try {
        for(Entry<String, String[]> parameter: parameters.entrySet()) {
          String parameterKey = parameter.getKey();
          Product product = null;
          if(parameterKey.startsWith("motherboard")) {
            product = MotherboardDAO.getInstance().findById(Integer.parseInt(parameterKey.split("_")[1]));
          }
          else if(parameterKey.startsWith("cpu")) {
            product = CpuDAO.getInstance().findById(Integer.parseInt(parameterKey.split("_")[1]));
          }
          else if(parameterKey.startsWith("hd")) {
            product = HdDAO.getInstance().findById(Integer.parseInt(parameterKey.split("_")[1]));
          }
          else if(parameterKey.startsWith("memory")) {
            product = MemoryDAO.getInstance().findById(Integer.parseInt(parameterKey.split("_")[1]));
          }
          else if(parameterKey.startsWith("computer")) {
            Cookie computerCookie = CookieHandler.findByName(request.getCookies(), "computers");
            int computerPosition = Integer.parseInt(parameterKey.split("_")[1]);
            String[] computerPartsIds = URLDecoder.decode(computerCookie.getValue(), "utf8")
              .split(";")[computerPosition].split("\\|");
            Computer computer = new Computer();
            computer.setId(computerPosition);
            computer.setMotherboard(MotherboardDAO.getInstance().findById(Integer.parseInt(computerPartsIds[0])));
            computer.setCpu(CpuDAO.getInstance().findById(Integer.parseInt(computerPartsIds[1])));
            computer.setHd(HdDAO.getInstance().findById(Integer.parseInt(computerPartsIds[2])));
            computer.setHdQuantity(Integer.parseInt(computerPartsIds[3]));
            computer.setMemory(MemoryDAO.getInstance().findById(Integer.parseInt(computerPartsIds[4])));
            computer.setMemoryQuantity(Integer.parseInt(computerPartsIds[5]));
            product = computer;
          }
          if(product != null) {
            order.addProduct(product, Integer.parseInt(parameter.getValue()[0]));
          }
        }
        OrderPriceCalculator orderPriceCalculator = new OrderPriceCalculator(order);
        orderPriceCalculator.calculate();
        request.setAttribute("order", order);
        request.setAttribute("total_price", orderPriceCalculator.getFormattedTotalPrice());
        request.setAttribute("discount", orderPriceCalculator.getFormattedDiscount());
        request.setAttribute("final_price", orderPriceCalculator.getFormattedFinalPrice());
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/orders/show.jsp");
        requestDispatcher.forward(request, response);
      }
      catch (NumberFormatException e) {
        e.printStackTrace();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
