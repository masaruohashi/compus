package br.com.compus.models;

import java.util.Calendar;

public class Report {
  private Calendar date;
  private int numSales;
  private double totalPrice;
  
  public Calendar getDate() {
    return date;
  }
  
  public void setDate(Calendar date) {
    this.date = date;
  }
  
  public int getNumSales() {
    return numSales;
  }
  
  public void setNumSales(int numSales) {
    this.numSales = numSales;
  }
  
  public double getTotalPrice() {
    return totalPrice;
  }
  
  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }
  
}
