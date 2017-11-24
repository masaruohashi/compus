package br.com.compus.models;

public abstract class ComputerPart {
  protected double price;

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getFormatedPrice() {
    return String.format("%.2f", price);
  }
}
