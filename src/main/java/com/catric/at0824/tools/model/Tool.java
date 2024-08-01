package com.catric.at0824.tools.model;

import java.math.BigDecimal;
import java.util.Map;
import static java.util.Map.entry;

public class Tool {

    // Parameters fed by checkout
    private final String toolCode;
    private final int rentalDayCount;
    private final int discountPercentage;
    private final String checkoutDate;

    // Private tool info
    // TODO: This would also be integrated into the backend via spring repository
    private Map<String, String> toolTypeMap = Map.ofEntries(
        entry("CHNS", "Chainsaw"),
        entry("LADW", "Ladder"),
        entry("JAKD", "Jackhammer"),
        entry("JAKR", "Jackhammer")
    );
    private Map<String, String> toolBrandMap = Map.ofEntries(
        entry("CHNS", "Stihl"),
        entry("LADW", "Werner"),
        entry("JAKD", "DeWalt"),
        entry("JAKR", "Ridgid")
    );
    private Map<String, BigDecimal> dailyChargeMap = Map.ofEntries(
        entry("Ladder", BigDecimal.valueOf(1.99)),
        entry("Chainsaw", BigDecimal.valueOf(1.49)),
        entry("Jackhammer", BigDecimal.valueOf(2.99))
    );
    private Map<String, Boolean> weekdayChargeMap = Map.ofEntries(
        entry("Ladder", true),
        entry("Chainsaw", true),
        entry("Jackhammer", true)
    );
    private Map<String, Boolean> weekendChargeMap = Map.ofEntries(
        entry("Ladder", true),
        entry("Chainsaw", false),
        entry("Jackhammer", false)
    );
    private Map<String, Boolean> holidayChargeMap = Map.ofEntries(
        entry("Ladder", false),
        entry("Chainsaw", true),
        entry("Jackhammer", false)
    );

    public Tool(
        final String toolCode,
        final int rentalDayCount,
        final int discountPercentage,
        final String checkoutDate
    ) {
        this.toolCode = toolCode;
        this.rentalDayCount = rentalDayCount;
        this.discountPercentage = discountPercentage;
        this.checkoutDate = checkoutDate;
    }

    public String getToolType() {
        return this.toolTypeMap.get(this.toolCode);
    }

    public String getToolBrand() {
        return this.toolBrandMap.get(this.toolCode);
    }

    public BigDecimal getDailyCharge() {
        String toolType = this.toolTypeMap.get(this.toolCode);
        return this.dailyChargeMap.get(toolType);
    }

    public Boolean isChargedOnWeekdays() {
        String toolType = this.toolTypeMap.get(this.toolCode);
        return this.weekdayChargeMap.get(toolType);
    }

    public Boolean isChargedOnWeekends() {
        String toolType = this.toolTypeMap.get(this.toolCode);
        return this.weekendChargeMap.get(toolType);
    }

    public Boolean isChargedOnHolidays() {
        String toolType = this.toolTypeMap.get(this.toolCode);
        return this.holidayChargeMap.get(toolType);
    }

    public String getToolCode() {
        return toolCode;
    }

    public int getRentalDayCount() {
        return rentalDayCount;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

}
