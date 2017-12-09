package br.com.compus.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
  private List<OrderItem> items;
  private double finalPrice;
  public double getFinalPrice() {
    return finalPrice;
  }

  public void setFinalPrice(double finalPrice) {
    this.finalPrice = finalPrice;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  private Client client;
  private Employee employee;

  public Order() {
    items = new ArrayList<OrderItem>();
  }
  
  public List<OrderItem> getItems() {
    return items;
  }

  public boolean isEmpty() {
    return items.isEmpty();
  }

  public void addProduct(Product product, int quantity) {
    items.add(new OrderItem(product, quantity));
  }

  public double totalPrice() {
    double price = 0;
    for(OrderItem item : items) {
      price += item.totalPrice();
    }
    return price;
  }
}
