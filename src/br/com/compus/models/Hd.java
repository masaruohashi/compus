package br.com.compus.models;

public class Hd extends ComputerPart{
  private String capacity;
  private String imageUrl;


  public Hd() {
    super();
  }

  public Hd(String name, double price, String capacity) {
    this.setName(name);
    this.setPrice(price);
    this.setCapacity(capacity);
  }

  public String getCapacity() {
    return capacity;
  }

  public void setCapacity(String capacity) {
    this.capacity = capacity;
  }

  public String productType() {
    return "hd";
  }
  
  public String getImageUrl() {
    return imageUrl;
  }
  
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
