package com.catric.at0824.tools.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class RentalAgreement {
    private String rentalAgreementUUID;
    private final Tool tool;
    private final String toolType;
    private final String toolBrand;
    private final String dueDate;
    private final int numChargeableDays;
    private final BigDecimal dailyRentalCharge;
    private final BigDecimal preDiscountCharge;
    private final BigDecimal discountAmount;
    private final BigDecimal finalChargeAmount;

    public RentalAgreement() {
        this.rentalAgreementUUID = null;
        this.tool = null;
        this.toolType = null;
        this.toolBrand = null;
        this.dueDate = null;
        this.dailyRentalCharge = null;
        this.numChargeableDays = 0;
        this.preDiscountCharge = null;
        this.discountAmount = null;
        this.finalChargeAmount = null;
    }

    public RentalAgreement(final Tool tool) {
        if (tool.getRentalDayCount() < 1) {
            throw new IllegalArgumentException("Error: rentalDayCount should be 1 or greater");
        }
        if (tool.getDiscountPercentage() > 100 || tool.getDiscountPercentage() < 0) {
            throw new IllegalArgumentException("Error: discountPercentage should be between 0-100%");
        }
        this.rentalAgreementUUID = UUID.randomUUID().toString();
        this.tool = tool;
        this.toolType = tool.getToolType();
        this.toolBrand = tool.getToolBrand();
        this.dueDate = generateDueDate(tool.getCheckoutDate(), tool.getRentalDayCount());
        this.dailyRentalCharge = this.tool.getDailyCharge();
        this.numChargeableDays = generateNumChargeableDays(this.tool);
        this.preDiscountCharge = this.dailyRentalCharge.multiply(BigDecimal.valueOf(this.numChargeableDays)).setScale(2, RoundingMode.HALF_UP);
        this.discountAmount = this.preDiscountCharge.multiply(BigDecimal.valueOf((this.tool.getDiscountPercentage())/100.0)).setScale(2, RoundingMode.HALF_UP);
        this.finalChargeAmount = this.preDiscountCharge.subtract(this.discountAmount).setScale(2, RoundingMode.HALF_UP);
    }

    public String getUUID() {
        return this.rentalAgreementUUID;
    }

    public void setUUID(String UUID) {
        this.rentalAgreementUUID = UUID;
    }

    public Tool getTool() {
        return this.tool;
    }

    public String getToolType() {
        return this.toolType;
    }

    public String getToolBrand() {
        return this.toolBrand;
    }

    public String getDueDate() {
        return this.dueDate;
    }
    
    public int getNumChargeableDays() {
        return this.numChargeableDays;
    }

    public BigDecimal getDailyRentalCharge() {
        return this.dailyRentalCharge;
    }

    public BigDecimal getPreDiscountCharge() {
        return this.preDiscountCharge;
    }

    public BigDecimal getDiscountAmount() {
        return this.discountAmount;
    }

    public BigDecimal getFinalChargeAmount() {
        return this.finalChargeAmount;
    }

    private String generateDueDate(final String checkoutDate, final int rentalDayCount) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        String resultingDate = "";
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(dateFormatter.parse(checkoutDate));
            c.add(Calendar.DATE, rentalDayCount);
            resultingDate = dateFormatter.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("ERROR: unhandled date input, input should be MM-dd-yyyy");
        }
        return resultingDate;
    }

    private int generateNumChargeableDays(final Tool tool){
        int numChargeableDays = tool.getRentalDayCount();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(dateFormatter.parse(tool.getCheckoutDate()));
            c.add(Calendar.DATE, 1);
            for(int i = 0; i < tool.getRentalDayCount(); i++) {
                if ((isWeekend(c)) && !tool.isChargedOnWeekends()) {
                    // If its a weekend and the tool doesn't charge on weekends, subtract chargeable days
                    numChargeableDays--;
                } 
                else if ((isHoliday(c)) && (!tool.isChargedOnHolidays())) {
                    // If its a holiday and tool doesn't charge on holidays, subtract chargeable days
                    numChargeableDays--;
                } else if (!tool.isChargedOnWeekdays()) {
                    // If its a weekday and tool doesn't charge on weekdays, subtract chargeable days
                    numChargeableDays--;
                }
                c.add(Calendar.DATE, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("ERROR: unhandled date input, input should be MM-dd-yyyy");
        }
        return numChargeableDays;
    }

    private boolean isWeekend(Calendar c) {
        return (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }

    private boolean isHoliday(Calendar c) {
        // TODO: Replace holiday lookups with bank holiday API lookup
        if (!isWeekend(c)) {
            if ((c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) && (c.get(Calendar.DAY_OF_MONTH) <= 7) && (c.get(Calendar.MONTH) == Calendar.SEPTEMBER)) {
                return true;
            }
            else if (
                (c.get(Calendar.MONTH) == Calendar.JULY)
                && (c.get(Calendar.DAY_OF_MONTH) == 4
                    || (
                        ((c.get(Calendar.DAY_OF_MONTH) == 5) && (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY))
                        || ((c.get(Calendar.DAY_OF_MONTH) == 3) && (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY))
                    )
            )) {
                return true;
            }
        }
        return false;
    }
}
