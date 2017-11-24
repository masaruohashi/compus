package br.com.compus.models;

public class Cpu extends ComputerPart{
  private String name;
  private String socket;

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
}
