package br.com.compus.models;

public abstract class ComputerPart extends Product{
  private double price;

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getFormattedPrice() {
    return String.format("%.2f", price);
  }
}
