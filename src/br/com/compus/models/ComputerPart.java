package br.com.compus.models;

public abstract class ComputerPart {
  protected float price;

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public String getFormatedPrice() {
    return String.format("%.2f", price);
  }
}
