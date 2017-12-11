package br.com.compus.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.compus.models.Computer;
import br.com.compus.models.Cpu;
import br.com.compus.models.Hd;
import br.com.compus.models.Memory;
import br.com.compus.models.Motherboard;

public class ComputerValidatorTest {
  private Computer computer;
  private Cpu cpu;
  private Motherboard motherboard;
  private Hd hd;
  private Memory memory;

  @Before
  public void setUp() {
    cpu = new Cpu("cpu", 12.50, "socket");
    motherboard = new Motherboard("motherboard", 11.50, "socket", 2, "DDR3");
    hd = new Hd("hd", 13.50, "1TB");
    memory = new Memory("memory", 14.50, "DDR3", "4GB");
    computer = new Computer(motherboard, cpu, hd, memory, 1, 2);
  }

  @Test
  public void testValidComputer() {
    boolean isValid = ComputerValidator.validate(computer);
    assertTrue(isValid);
  }

  @Test
  public void testInvalidCpuSocket() {
    cpu.setSocket("invalidSocket");
    boolean isValid = ComputerValidator.validate(computer);
    assertFalse(isValid);
  }

  @Test
  public void testInvalidMemoryType() {
    memory.setType("invalidType");
    boolean isValid = ComputerValidator.validate(computer);
    assertFalse(isValid);
  }

  @Test
  public void testInvalidMemoryQuantity() {
    computer.setMemoryQuantity(100);
    boolean isValid = ComputerValidator.validate(computer);
    assertFalse(isValid);
  }

  @Test
  public void testNullValues() {
    computer.setHd(null);
    boolean isValid = ComputerValidator.validate(computer);
    assertFalse(isValid);
  }
}
