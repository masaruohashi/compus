package br.com.compus.models;

public class Cpu extends ComputerPart{
  private String socket;
  private String imageUrl;

  public Cpu() {
    super();
  }

  public Cpu(String name, double price, String socket) {
    this.setName(name);
    this.setSocket(socket);
    this.setPrice(price);
  }

  public String getSocket() {
    return socket;
  }

  public void setSocket(String socket) {
    this.socket = socket;
  }

  public String productType() {
    return "cpu";
  }
  
  public String getImageUrl() {
	  return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
	  this.imageUrl = imageUrl;
  }
}
