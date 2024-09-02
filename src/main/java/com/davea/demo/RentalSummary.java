package com.davea.demo;

import java.time.LocalDate;

class RentalSummary {
    private Tool rentalTool;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int chargeDays;
    private double preDiscountCharge;
    private int discountPercent;
    private double discountAmount;
    private double finalCharge;

    public RentalSummary(Tool rentalTool, int rentalDays, LocalDate checkoutDate, LocalDate dueDate, int chargeDays, double preDiscountCharge, int discountPercent, double discountAmount, double finalCharge) {
        this.rentalTool = rentalTool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public Tool getRentalTool() {
        return this.rentalTool;
    }

    public int getRentalDays() {
        return this.rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return this.checkoutDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public int getChargeDays() {
        return this.chargeDays;
    }

    public double getPreDiscountCharge() {
        return this.preDiscountCharge;
    }

    public int getDiscountPercent() {
        return this.discountPercent;
    }

    public double getDiscountAmount() {
        return this.discountAmount;
    }

    public double getFinalCharge() {
        return this.finalCharge;
    }

    public void printSummary() {
        System.out.println("Rental Summary:");
        System.out.println("Tool Code: " + rentalTool.getToolCode());
        System.out.println("Tool Type: " + rentalTool.getToolType());
        System.out.println("Tool Brand: " + rentalTool.getBrand());
        System.out.println("Rental Days: " + rentalDays);
        System.out.println("Checkout Date: " + checkoutDate);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Daily Rental Charge: $" + rentalTool.getDailyCharge());
        System.out.println("Charge Days: " + chargeDays);
        System.out.println("Pre-discount Charge: $" + preDiscountCharge);
        System.out.println("Discount Percent: " + discountPercent + "%");
        System.out.println("Discount Amount: $" + discountAmount);
        System.out.println("Final Charge: $" + finalCharge);
    }
}