package br.com.compus.models;

public abstract class Product {
  private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFormattedPrice() {
    return String.format("%.2f", this.getPrice());
  }

  public abstract String getName();

  public abstract double getPrice();

  public abstract String productType();
}
