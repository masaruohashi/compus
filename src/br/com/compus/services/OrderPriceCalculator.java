package br.com.compus.services;

import br.com.compus.models.Order;
import br.com.compus.models.OrderItem;

public class OrderPriceCalculator {
  private Order order;
  private double totalPrice;
  private double discountPercentage;
  private int motherboardsQuantity;
  private int cpusQuantity;
  private int hdsQuantity;
  private int memoriesQuantity;
  private int computersQuantity;

  public OrderPriceCalculator(Order order) {
    this.order = order;
    discountPercentage = 0;
    motherboardsQuantity = 0;
    cpusQuantity = 0;
    hdsQuantity = 0;
    memoriesQuantity = 0;
    computersQuantity = 0;
  }

  public void calculate() {
    totalPrice = 0;
    for(OrderItem item : order.getItems()) {
      totalPrice += item.totalPrice();
      calculateDiscountPercentage(item);
    }
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public double getDiscount() {
    return totalPrice * discountPercentage;
  }

  public double getFinalPrice() {
    return totalPrice - getDiscount();
  }

  public String getFormattedTotalPrice() {
    return String.format("%.2f", getTotalPrice());
  }

  public String getFormattedDiscount() {
    return String.format("%.2f", getDiscount());
  }

  public String getFormattedFinalPrice() {
    return String.format("%.2f", getFinalPrice());
  }

  private void calculateDiscountPercentage(OrderItem item) {
    switch(item.getProduct().productType()) {
      case "motherboard":
        motherboardsQuantity += item.getQuantity();
        calculateMotherboardDiscount();
        break;
      case "cpu":
        cpusQuantity += item.getQuantity();
        calculateCpuDiscount();
        break;
      case "memory":
        memoriesQuantity += item.getQuantity();
        calculateMemoryDiscount();
        break;
      case "hd":
        hdsQuantity += item.getQuantity();
        calculateHdDiscount();
        break;
      case "computer":
        computersQuantity += item.getQuantity();
        calculateComputerDiscount();
        break;
    }
  }

  private void calculateMotherboardDiscount() {
    double calculatedDiscountPercentage = 0;
    if(motherboardsQuantity >= 5)
      calculatedDiscountPercentage = 0.08;
    else if(motherboardsQuantity >= 1) {
      if(cpusQuantity >= 1 || hdsQuantity >= 1)
        calculatedDiscountPercentage = 0.05;
    }
    compareDiscountsPercentages(calculatedDiscountPercentage);
  }

  private void calculateCpuDiscount() {
    double calculatedDiscountPercentage = 0;
    if(cpusQuantity >= 5)
      calculatedDiscountPercentage = 0.07;
    else if(cpusQuantity >= 1 && motherboardsQuantity >= 1)
      calculatedDiscountPercentage = 0.05;
    compareDiscountsPercentages(calculatedDiscountPercentage);
  }

  private void calculateMemoryDiscount() {
    double calculatedDiscouvntPercentage = 0;
    if(memoriesQuantity >= 8)
      calculatedDiscouvntPercentage = 0.1;
    compareDiscountsPercentages(calculatedDiscouvntPercentage);
  }

  private void calculateHdDiscount() {
    double calculatedDiscouvntPercentage = 0;
    if(hdsQuantity >= 5)
      calculatedDiscouvntPercentage = 0.07;
    compareDiscountsPercentages(calculatedDiscouvntPercentage);
  }

  private void calculateComputerDiscount() {
    double calculatedDiscouvntPercentage = 0;
    if(computersQuantity >= 5)
      calculatedDiscouvntPercentage = 0.15;
    else
      calculatedDiscouvntPercentage = 0.1;
    compareDiscountsPercentages(calculatedDiscouvntPercentage);
  }

  private void compareDiscountsPercentages(double calculatedDiscountPercentage) {
    if(calculatedDiscountPercentage > discountPercentage)
      discountPercentage = calculatedDiscountPercentage;
  }
}
