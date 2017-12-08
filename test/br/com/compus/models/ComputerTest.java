package br.com.compus.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ComputerTest {
  private Computer computer;
  private Motherboard motherboard;
  private Cpu cpu;
  private Hd hd;
  private Memory memory;

  @Before
  public void setUp() {
    motherboard = new Motherboard();
    cpu = new Cpu();
    hd = new Hd();
    memory = new Memory();
    motherboard.setName("motherboard");
    motherboard.setPrice(12.25);
    cpu.setName("cpu");
    cpu.setPrice(85.96);
    hd.setName("hd");
    hd.setPrice(589.69);
    hd.setCapacity("1TB");
    memory.setName("memory");
    memory.setCapacity("2GB");
    memory.setType("DDR3");
    memory.setPrice(8579.96);
    computer = new Computer();
  }

  @Test
  public void testCpu() {
    computer.setCpu(cpu);
    assertEquals(cpu, computer.getCpu());
  }

  @Test
  public void testMotherboard() {
    computer.setMotherboard(motherboard);
    assertEquals(motherboard, computer.getMotherboard());
  }

  @Test
  public void testHd() {
    computer.setHd(hd);
    computer.setHdQuantity(2);
    assertEquals(hd, computer.getHd());
    assertEquals(2, computer.getHdQuantity());
  }

  @Test
  public void testMemory() {
    computer.setMemory(memory);
    computer.setMemoryQuantity(4);
    assertEquals(memory, computer.getMemory());
    assertEquals(4, computer.getMemoryQuantity());
  }

  @Test
  public void testComputerName() {
    computer.setCpu(cpu);
    computer.setHd(hd);
    computer.setMemory(memory);
    computer.setHdQuantity(1);
    computer.setMemoryQuantity(1);
    assertEquals("cpu, 1x HD 1TB, 1x Memória DDR3 2GB", computer.getName());
  }

  @Test
  public void testComputerPrice() {
    computer.setCpu(cpu);
    computer.setHd(hd);
    computer.setMemory(memory);
    computer.setMotherboard(motherboard);
    computer.setHdQuantity(1);
    computer.setMemoryQuantity(1);
    assertEquals(9567.86, computer.getPrice(), 10E-2);
    assertEquals("9567,86", computer.getFormattedPrice());
  }
}
