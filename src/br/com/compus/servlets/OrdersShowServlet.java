package br.com.compus.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.compus.dao.OrderDAO;
import br.com.compus.decorators.OrderDecorator;
import br.com.compus.models.Order;

@WebServlet("/pedidos")
public class OrdersShowServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;

  public OrdersShowServlet() {
    super();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("admin_name") == null) {
      response.sendRedirect(request.getContextPath() + "/login");
    } else {
      String orderId = request.getParameter("id");
      if(orderId == null) {
        showAll(request, response);
      } else {
        showById(request, response);
      }
    }
  }

  private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<OrderDecorator> orders = new ArrayList<OrderDecorator>();
    for(Order order : OrderDAO.getInstance().getAll()) {
      orders.add(new OrderDecorator(order));
    }
    request.setAttribute("orders", orders);
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/orders/list.jsp");
    requestDispatcher.forward(request, response);
  }

  private void showById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int orderId = Integer.parseInt(request.getParameter("id"));
    OrderDecorator order = new OrderDecorator(OrderDAO.getInstance().findById(orderId));
    request.setAttribute("order", order);
    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/views/orders/order/show.jsp");
    requestDispatcher.forward(request, response);
  }
}
