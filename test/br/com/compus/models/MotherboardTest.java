package br.com.compus.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MotherboardTest {
  private Motherboard mockedMotherboard;

  @Before
  public void setUp() {
    mockedMotherboard = new Motherboard();
  }

  @Test
  public void testId() {
    mockedMotherboard.setId(3);
    assertEquals(3, mockedMotherboard.getId());
  }

  @Test
  public void testName() {
    mockedMotherboard.setName("name");
    assertEquals("name", mockedMotherboard.getName());
  }

  @Test
  public void testSocket() {
    mockedMotherboard.setSocket("socket");
    assertEquals("socket", mockedMotherboard.getSocket());
  }

  @Test
  public void testMemorySlots() {
    mockedMotherboard.setMemorySlots(2);
    assertEquals(2, mockedMotherboard.getMemorySlots());
  }

  @Test
  public void testMemoryType() {
    mockedMotherboard.setMemoryType("DDR3");
    assertEquals("DDR3", mockedMotherboard.getMemoryType());
  }

  @Test
  public void testPrice() {
    mockedMotherboard.setPrice(12.30);
    assertEquals(12.30, mockedMotherboard.getPrice(), 10E-2);
  }

  @Test
  public void testFormattedPrice() {
    mockedMotherboard.setPrice(1.50);
    assertEquals("1,50", mockedMotherboard.getFormatedPrice());
  }
}
