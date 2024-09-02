package com.davea.demo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

public class Checkout {
    // ideally this would be in a database or accessed via an API. but for simplicity in the demo just hardcoding it
    private static Tool[] tools = {
        new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true),
        new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false),
        new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false),
        new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false)
    };

    // this could be the function that finds a tool via API or database query
    private static Tool findRentalTool(String toolCode) throws Exception {
        Tool rentalTool = null;
        for (Tool tool : tools) {
            if (tool.getToolCode().equals(toolCode)) {
                rentalTool = tool;
            }
        }
        if (rentalTool == null) {
            throw new Exception("Tool not found");
        }
        return rentalTool;
    }

    // Define the holidays
    private static Set<LocalDate> getHolidays(int year) {
        final Set<LocalDate> holidays = new HashSet<>();
        // July 4th
        holidays.add(LocalDate.of(year, Month.JULY, 4));
        // First Monday of September (Labor Day)
        holidays.add(LocalDate.of(year, Month.SEPTEMBER, 1).with(java.time.temporal.TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
        return holidays;
    }

    public static RentalSummary buildRentalSummary(LocalDate checkoutDate, int rentalDays, Tool tool, int discountPercent) {
        double totalCost = 0.0;
        LocalDate currentDate = checkoutDate;
        final LocalDate dueDate = checkoutDate.plusDays(rentalDays - 1);
        int chargeDays = 0;

        final Set<LocalDate> holidays = getHolidays(checkoutDate.getYear());

        for (int i = 0; i < rentalDays; i++) {
            final DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            final boolean isHoliday = holidays.contains(currentDate);

            if (isHoliday && tool.getHolidayCharge()) {
                totalCost += tool.getDailyCharge();
                chargeDays++;
            } else if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                if (tool.getWeekendCharge()) {
                    totalCost += tool.getDailyCharge();
                    chargeDays++;
                }
            } else {
                if (tool.getWeekdayCharge()) {
                    totalCost += tool.getDailyCharge();
                    chargeDays++;
                }
            }

            // Move to the next day
            currentDate = currentDate.plusDays(1);
        }

        // Round the pre-discount charge to the nearest cent
        final double preDiscountCharge = Math.round(totalCost * 100.0) / 100.0;
        // Calculate discount amount
        final double discountAmount = Math.round((preDiscountCharge * discountPercent / 100.0) * 100.0) / 100.0;
        // Calculate final charge
        final double finalCharge = Math.round((preDiscountCharge - discountAmount) * 100.0) / 100.0;

        // Return the rental summary
        return new RentalSummary(tool, rentalDays, checkoutDate, dueDate, chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);
    }

    static RentalSummary checkout(String toolCode, int rentalDays, LocalDate checkoutDate, int discountPercent) throws Exception {
        final Tool rentalTool = findRentalTool(toolCode);
        final RentalSummary rentalSummary = buildRentalSummary(checkoutDate, rentalDays, rentalTool, discountPercent);
        return rentalSummary;
    }
}
