package br.com.compus.models;

public abstract class ComputerPart extends Product{
  private float price;

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
