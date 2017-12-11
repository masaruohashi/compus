package br.com.compus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.compus.models.Computer;
import br.com.compus.models.OrderItem;
import br.com.compus.models.Product;

public class OrderItemDAO extends BaseDAO{
  public OrderItemDAO() {
    super();
  }

  public static OrderItemDAO getInstance() {
    return new OrderItemDAO();
  }

  public boolean create(OrderItem orderItem, int orderId) {
    String sql = "INSERT INTO order_item (order_id, product_id, product_type, quantity)" +
        "VALUES (?, ?, ?, ?)";
    try {
      int productId;
      PreparedStatement statement = this.connection.prepareStatement(sql);
      if(orderItem.getProduct().productType().equals("computer")) {
        productId = ComputerDAO.getInstance().create((Computer) orderItem.getProduct());
      } else {
        productId = orderItem.getProduct().getId();
      }
      statement.setInt(1, orderId);
      statement.setInt(2, productId);
      statement.setString(3, orderItem.getProduct().productType());
      statement.setInt(4, orderItem.getQuantity());
      statement.execute();
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public List<OrderItem> findAllByOrderId(int orderId) {
    List<OrderItem> items = new ArrayList<OrderItem>();
    String sql = "SELECT * FROM order_item WHERE order_id = ?";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, orderId);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(findByTypeAndId(result.getString("product_type"), result.getInt("product_id")));
        orderItem.setQuantity(result.getInt("quantity"));
        items.add(orderItem);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return items;
  }

  private Product findByTypeAndId(String type, int id) throws SQLException{
    if(type.equals("motherboard")) {
      return MotherboardDAO.getInstance().findById(id);
    } else if(type.equals("cpu")) {
      return CpuDAO.getInstance().findById(id);
    } else if(type.equals("memory")) {
      return MemoryDAO.getInstance().findById(id);
    } else if(type.equals("hd")) {
      return HdDAO.getInstance().findById(id);
    } else {
      return ComputerDAO.getInstance().findById(id);
    }
  }
}
