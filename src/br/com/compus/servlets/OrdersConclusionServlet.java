package br.com.compus.servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import br.com.compus.dao.ClientDAO;
import br.com.compus.dao.CpuDAO;
import br.com.compus.dao.EmployeeDAO;
import br.com.compus.dao.HdDAO;
import br.com.compus.dao.MemoryDAO;
import br.com.compus.dao.MotherboardDAO;
import br.com.compus.dao.OrderDAO;
import br.com.compus.models.Computer;
import br.com.compus.models.Order;
import br.com.compus.models.Product;
import br.com.compus.services.CookieHandler;
import br.com.compus.services.OrderPriceCalculator;

@WebServlet("/pedido/novo")
public class OrdersConclusionServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public OrdersConclusionServlet() {
    super();
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
        order.setClient(ClientDAO.getInstance().findByCpf(session.getAttribute("client_cpf").toString()));
        order.setEmployee(EmployeeDAO.getInstance().findByCpf(session.getAttribute("employee_cpf").toString()));;
        order.setFinalPrice(orderPriceCalculator.getFinalPrice());
        session.invalidate();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> data = new HashMap<String, String>();
        if(OrderDAO.getInstance().createOrder(order)) {
          data.put("message", "Sucesso ao criar o pedido!");
        } else {
          data.put("message", "Falha ao criar o pedido.");
        }
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
}
