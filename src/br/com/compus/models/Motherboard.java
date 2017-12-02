package br.com.compus.models;

public class Motherboard extends ComputerPart{
  private String name;
  private String socket;
  private int memorySlots;
  private String memoryType;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
}
