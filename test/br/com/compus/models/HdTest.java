package br.com.compus.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HdTest {
  private Hd mockedHd;

  @Before
  public void setUp() {
    mockedHd = new Hd();
  }

  @Test
  public void testId() {
    mockedHd.setId(5);
    assertEquals(5, mockedHd.getId());
  }

  @Test
  public void testName() {
    mockedHd.setName("name");
    assertEquals("name", mockedHd.getName());
  }

  @Test
  public void testCapacity() {
    mockedHd.setCapacity("2TB");
    assertEquals("2TB", mockedHd.getCapacity());
  }

  @Test
  public void testPrice() {
    mockedHd.setPrice(12.30);
    assertEquals(12.30, mockedHd.getPrice(), 10E-2);
  }

  @Test
  public void testFormattedPrice() {
    mockedHd.setPrice(1.5);
    assertEquals("1,50", mockedHd.getFormatedPrice());
  }
}
