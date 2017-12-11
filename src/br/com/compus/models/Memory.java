package br.com.compus.models;

public class Memory extends ComputerPart{
  private String type;
  private String capacity;
  private String imageUrl;

  public Memory() {
    super();
  }

  public Memory(String name, double price, String type, String capacity) {
    this.setName(name);
    this.setPrice(price);
    this.setType(type);
    this.setCapacity(capacity);
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
  public String getCapacity() {
    return capacity;
  }
  
  public void setCapacity(String capacity) {
    this.capacity = capacity;
  }

  public String productType() {
    return "memory";
  }
  
  public String getImageUrl() {
    return imageUrl;
  }
  
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
