package br.com.compus.decorators;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.compus.models.Order;
import br.com.compus.models.OrderItem;

public class OrderDecorator {
  private Order order;

  public OrderDecorator(Order order) {
    this.order = order;
  }

  public int id() {
    return order.getId();
  }

  public String client() {
    return order.getClient().getName();
  }

  public String seller() {
    return order.getEmployee().getName();
  }

  public String finalPrice() {
    return String.format("%.2f", order.getFinalPrice());
  }

  public String date() {
    String formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(order.getDate());
    return formattedDate;
  }

  public List<OrderItem> items() {
    return order.getItems();
  }
}
