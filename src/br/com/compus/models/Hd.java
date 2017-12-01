package br.com.compus.models;

public class Hd extends ComputerPart{
  private String name;
  private String capacity;
  private String imageUrl;


public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCapacity() {
    return capacity;
  }

  public void setCapacity(String capacity) {
    this.capacity = capacity;
  }
  
  public String getImageUrl() {
    return imageUrl;
  }
  
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
