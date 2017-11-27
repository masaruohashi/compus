package br.com.compus.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MemoryTest {
  private Memory mockedMemory;

  @Before
  public void setUp() {
    mockedMemory = new Memory();
  }

  @Test
  public void testId() {
    mockedMemory.setId(1);
    assertEquals(1, mockedMemory.getId());
  }

  @Test
  public void testName() {
    mockedMemory.setName("name");
    assertEquals("name", mockedMemory.getName());
  }

  @Test
  public void testType() {
    mockedMemory.setType("DDR4");
    assertEquals("DDR4", mockedMemory.getType());
  }

  @Test
  public void testCapacity() {
    mockedMemory.setCapacity("1TB");
    assertEquals("1TB", mockedMemory.getCapacity());
  }

  @Test
  public void testPrice() {
    mockedMemory.setPrice(12.30);
    assertEquals(12.30, mockedMemory.getPrice(), 10E-2);
  }

  @Test
  public void testFormattedPrice() {
    mockedMemory.setPrice(1.50);
    assertEquals("1,50", mockedMemory.getFormattedPrice());
  }
}
