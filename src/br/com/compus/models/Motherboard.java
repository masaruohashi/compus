package br.com.compus.models;

public class Motherboard extends ComputerPart{
  private String socket;
  private int memorySlots;
  private String memoryType;

  public Motherboard() {
    super();
  }

  public Motherboard(String name, Double price, String socket, int memorySlots, String memoryType) {
    this.setName(name);
    this.setPrice(price);
    this.setSocket(socket);
    this.setMemorySlots(memorySlots);
    this.setMemoryType(memoryType);
  }

  public String getSocket() {
    return socket;
  }

  public void setSocket(String socket) {
    this.socket = socket;
  }
  
  public int getMemorySlots() {
    return memorySlots;
  }
  
  public void setMemorySlots(int memorySlots) {
    this.memorySlots = memorySlots;
  }
  
  public String getMemoryType() {
    return memoryType;
  }
  
  public void setMemoryType(String memoryType) {
    this.memoryType = memoryType;
  }

  public String productType() {
    return "motherboard";
  }
}
