package br.com.compus.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

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
}
