package br.com.compus.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.compus.models.Order;
import br.com.compus.models.OrderItem;

public class OrderDAO extends BaseDAO {

  public OrderDAO() {
    super();
  }

  public static OrderDAO getInstance() {
    return new OrderDAO();
  }

  public boolean createOrder(Order order) throws SQLException {
    String sql = "INSERT INTO `order` (final_price, date, employee_id, client_id)" +
                 "VALUES (?, ?, ?, ?)";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      statement.setDouble(1, order.totalPrice());
      statement.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
      statement.setInt(3, order.getEmployee().getId());
      statement.setInt(4, order.getClient().getId());
      statement.execute();
      ResultSet result = statement.getGeneratedKeys();
      result.next();
      for(OrderItem orderItem : order.getItems()) {
        OrderItemDAO.getInstance().create(orderItem, result.getInt(1));
      }
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public List<Order> getAll() {
    List<Order> orders = new ArrayList<Order>();
    String sql = "SELECT * FROM `order` ORDER BY id DESC";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        Order order = new Order();
        order.setId(result.getInt("id"));
        order.setDate(result.getDate("date"));
        order.setFinalPrice(result.getDouble("final_price"));
        order.setClient(ClientDAO.getInstance().findById(result.getInt("client_id")));
        order.setEmployee(EmployeeDAO.getInstance().findById(result.getInt("employee_id")));
        orders.add(order);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orders;
  }

  public Order findById(int id) {
    Order order = null;
    String sql = "SELECT * FROM `order` WHERE id = ?";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        order = new Order();
        order.setId(id);
        order.setClient(ClientDAO.getInstance().findById(result.getInt("client_id")));
        order.setEmployee(EmployeeDAO.getInstance().findById(result.getInt("employee_id")));
        order.setDate(result.getDate("date"));
        order.setItems(OrderItemDAO.getInstance().findAllByOrderId(id));
        order.setFinalPrice(result.getDouble("final_price"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return order;
  }
}
