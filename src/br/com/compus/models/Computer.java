package br.com.compus.models;

public class Computer extends Product {
  private Motherboard motherboard;
  private Cpu cpu;
  private Hd hd;
  private Memory memory;
  private int hdQuantity;
  private int memoryQuantity;

  public Computer() {
    super();
  }

  public Computer(Motherboard motherboard, Cpu cpu, Hd hd, Memory memory, int hdQuantity, int memoryQuantity) {
    this.motherboard = motherboard;
    this.cpu = cpu;
    this.hd = hd;
    this.memory = memory;
    this.hdQuantity = hdQuantity;
    this.memoryQuantity = memoryQuantity;
  }

  public Motherboard getMotherboard() {
    return motherboard;
  }

  public void setMotherboard(Motherboard motherboard) {
    this.motherboard = motherboard;
  }

  public Cpu getCpu() {
    return cpu;
  }

  public void setCpu(Cpu cpu) {
    this.cpu = cpu;
  }

  public Hd getHd() {
    return hd;
  }

  public void setHd(Hd hd) {
    this.hd = hd;
  }

  public Memory getMemory() {
    return memory;
  }

  public void setMemory(Memory memory) {
    this.memory = memory;
  }

  public int getHdQuantity() {
    return hdQuantity;
  }

  public void setHdQuantity(int hdQuantity) {
    this.hdQuantity = hdQuantity;
  }

  public int getMemoryQuantity() {
    return memoryQuantity;
  }

  public void setMemoryQuantity(int memoryQuantity) {
    this.memoryQuantity = memoryQuantity;
  }

  public String getName() {
    String name = this.cpu.getName() + ", " +
      this.hdQuantity + "x HD " + this.hd.getCapacity() + ", " +
      this.memoryQuantity + "x Memória " + this.memory.getType() + " " + this.memory.getCapacity();
    return name;
  }

  public double getPrice() {
    double price = this.cpu.getPrice() + this.motherboard.getPrice();
    price += this.hd.getPrice() * this.hdQuantity;
    price += this.memory.getPrice() * this.memoryQuantity;
    price += 300;
    return price;
  }

  public String productType() {
    return "computer";
  }
}
