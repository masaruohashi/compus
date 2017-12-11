package br.com.compus.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
  private int id;
  private List<OrderItem> items;
  private double finalPrice;
  private Date date;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setItems(List<OrderItem> items) {
    this.items = items;
  }

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
