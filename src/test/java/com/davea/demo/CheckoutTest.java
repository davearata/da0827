package com.davea.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class CheckoutTest {
    @Test
    public void testCheckout() {
        RentalSummary rentalSummary = null;
        try {
            rentalSummary = Checkout.checkout("CHNS", 5, LocalDate.of(2024, 9, 1), 30);
        } catch (Exception error) {
            error.printStackTrace();
            fail("Should not have thrown any exception"); 
        }
        if (rentalSummary == null) {
            fail("Failed to build rental summary");
        }
        assertEquals(rentalSummary.getRentalTool().getToolType(), "Chainsaw");
        assertEquals(rentalSummary.getRentalTool().getBrand(), "Stihl");
        assertTrue(rentalSummary.getCheckoutDate().equals(LocalDate.of(2024, 9, 1)));
        assertTrue(rentalSummary.getDueDate().equals(LocalDate.of(2024, 9, 5)));
        assertEquals(rentalSummary.getRentalTool().getDailyCharge(), 1.49);
        assertEquals(rentalSummary.getChargeDays(), 4);
        assertEquals(rentalSummary.getPreDiscountCharge(), 5.96);
        assertEquals(rentalSummary.getDiscountPercent(), 30);
        assertEquals(rentalSummary.getDiscountAmount(), 1.79);
        assertEquals(rentalSummary.getFinalCharge(), 4.17);
    }

    @Test
    public void test3() {
        RentalSummary rentalSummary = null;
        try {
            rentalSummary = Checkout.checkout("CHNS", 5, LocalDate.of(2015, 7, 2), 25);
        } catch (Exception error) {
            error.printStackTrace();
            fail("Should not have thrown any exception"); 
        }
        if (rentalSummary == null) {
            fail("Failed to build rental summary");
        }
        assertEquals(rentalSummary.getRentalTool().getToolType(), "Chainsaw");
        assertEquals(rentalSummary.getRentalTool().getBrand(), "Stihl");
        assertTrue(rentalSummary.getCheckoutDate().equals(LocalDate.of(2015, 7, 2)));
        assertTrue(rentalSummary.getDueDate().equals(LocalDate.of(2015, 7, 6)));
        assertEquals(rentalSummary.getRentalTool().getDailyCharge(), 1.49);
        assertEquals(rentalSummary.getChargeDays(), 4);
        assertEquals(rentalSummary.getPreDiscountCharge(), 5.96);
        assertEquals(rentalSummary.getDiscountPercent(), 25);
        assertEquals(rentalSummary.getDiscountAmount(), 1.49);
        assertEquals(rentalSummary.getFinalCharge(), 4.47);
    }

    @Test
    public void test4() {
        RentalSummary rentalSummary = null;
        try {
            rentalSummary = Checkout.checkout("JAKD", 6, LocalDate.of(2015, 9, 3), 0);
        } catch (Exception error) {
            error.printStackTrace();
            fail("Should not have thrown any exception"); 
        }
        if (rentalSummary == null) {
            fail("Failed to build rental summary");
        }
        assertEquals(rentalSummary.getRentalTool().getToolType(), "Jackhammer");
        assertEquals(rentalSummary.getRentalTool().getBrand(), "DeWalt");
        assertTrue(rentalSummary.getCheckoutDate().equals(LocalDate.of(2015, 9, 3)));
        assertTrue(rentalSummary.getDueDate().equals(LocalDate.of(2015, 9, 8)));
        assertEquals(rentalSummary.getRentalTool().getDailyCharge(), 2.99);
        assertEquals(rentalSummary.getChargeDays(), 4);
        assertEquals(rentalSummary.getPreDiscountCharge(), 11.96);
        assertEquals(rentalSummary.getDiscountPercent(), 0);
        assertEquals(rentalSummary.getDiscountAmount(), 0);
        assertEquals(rentalSummary.getFinalCharge(), 11.96);
    }

    @Test
    public void test5() {
        RentalSummary rentalSummary = null;
        try {
            rentalSummary = Checkout.checkout("JAKR", 9, LocalDate.of(2015, 7, 2), 0);
        } catch (Exception error) {
            error.printStackTrace();
            fail("Should not have thrown any exception"); 
        }
        if (rentalSummary == null) {
            fail("Failed to build rental summary");
        }
        assertEquals(rentalSummary.getRentalTool().getToolType(), "Jackhammer");
        assertEquals(rentalSummary.getRentalTool().getBrand(), "Ridgid");
        assertTrue(rentalSummary.getCheckoutDate().equals(LocalDate.of(2015, 7, 2)));
        assertTrue(rentalSummary.getDueDate().equals(LocalDate.of(2015, 7, 10)));
        assertEquals(rentalSummary.getRentalTool().getDailyCharge(), 2.99);
        assertEquals(rentalSummary.getChargeDays(), 7);
        assertEquals(rentalSummary.getPreDiscountCharge(), 20.93);
        assertEquals(rentalSummary.getDiscountPercent(), 0);
        assertEquals(rentalSummary.getDiscountAmount(), 0);
        assertEquals(rentalSummary.getFinalCharge(), 20.93);
    }

    @Test
    public void test6() {
        RentalSummary rentalSummary = null;
        try {
            rentalSummary = Checkout.checkout("JAKR", 4, LocalDate.of(2020, 7, 2), 50);
        } catch (Exception error) {
            error.printStackTrace();
            fail("Should not have thrown any exception"); 
        }
        if (rentalSummary == null) {
            fail("Failed to build rental summary");
        }
        assertEquals(rentalSummary.getRentalTool().getToolType(), "Jackhammer");
        assertEquals(rentalSummary.getRentalTool().getBrand(), "Ridgid");
        assertTrue(rentalSummary.getCheckoutDate().equals(LocalDate.of(2020, 7, 2)));
        assertTrue(rentalSummary.getDueDate().equals(LocalDate.of(2020, 7, 5)));
        assertEquals(rentalSummary.getRentalTool().getDailyCharge(), 2.99);
        assertEquals(rentalSummary.getChargeDays(), 2);
        assertEquals(rentalSummary.getPreDiscountCharge(), 5.98);
        assertEquals(rentalSummary.getDiscountPercent(), 50);
        assertEquals(rentalSummary.getDiscountAmount(), 2.99);
        assertEquals(rentalSummary.getFinalCharge(), 2.99);
    }
}