package br.com.compus.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CpuTest {
  private Cpu mockedCpu;

  @Before
  public void setUp() {
    mockedCpu = new Cpu();
  }

  @Test
  public void testId() {
    mockedCpu.setId(2);
    assertEquals(2, mockedCpu.getId());
  }

  @Test
  public void testName() {
    mockedCpu.setName("name");
    assertEquals("name", mockedCpu.getName());
  }

  @Test
  public void testSocket() {
    mockedCpu.setSocket("socket");
    assertEquals("socket", mockedCpu.getSocket());
  }

  @Test
  public void testPrice() {
    mockedCpu.setPrice(12.30);
    assertEquals(12.30, mockedCpu.getPrice(), 10E-2);
  }

  @Test
  public void testFormattedPrice() {
    mockedCpu.setPrice(1.50);
    assertEquals("1,50", mockedCpu.getFormatedPrice());
  }
}
