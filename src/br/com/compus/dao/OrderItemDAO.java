package br.com.compus.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.compus.models.OrderItem;

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
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, orderId);
      statement.setInt(2, orderItem.getProduct().getId());
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
}
