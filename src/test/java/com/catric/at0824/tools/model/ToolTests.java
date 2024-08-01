package com.catric.at0824.tools.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class ToolTests {
    @Autowired
    private Tool tool1;
    private Tool tool2;
    private Tool tool3;
    private Tool tool4;
    private Tool tool5;
    private Tool tool6;

    @Test
    public void Tool_CreateTool1() throws Exception {
        tool1 = new Tool("JAKR", 5, 101, "09-03-2015");
        assertEquals(tool1.getToolType(), "Jackhammer");
        assertEquals(tool1.getToolBrand(), "Ridgid");
        assertEquals(tool1.getDailyCharge(), BigDecimal.valueOf(2.99));
        assertEquals(tool1.isChargedOnWeekdays(), true);
        assertEquals(tool1.isChargedOnWeekends(), false);
        assertEquals(tool1.isChargedOnHolidays(), false);
        assertEquals(tool1.getToolCode(), "JAKR");
        assertEquals(tool1.getRentalDayCount(), 5);
        assertEquals(tool1.getDiscountPercentage(), 101);
        assertEquals(tool1.getCheckoutDate(), "09-03-2015");
    }

    @Test
    public void RentalAgreement_CreateRentalAgreement2() throws Exception {
        tool2 = new Tool("LADW", 3, 10, "07-02-2020");
        assertEquals(tool2.getToolType(), "Ladder");
        assertEquals(tool2.getToolBrand(), "Werner");
        assertEquals(tool2.getDailyCharge(), BigDecimal.valueOf(1.99));
        assertEquals(tool2.isChargedOnWeekdays(), true);
        assertEquals(tool2.isChargedOnWeekends(), true);
        assertEquals(tool2.isChargedOnHolidays(), false);
        assertEquals(tool2.getToolCode(), "LADW");
        assertEquals(tool2.getRentalDayCount(), 3);
        assertEquals(tool2.getDiscountPercentage(), 10);
        assertEquals(tool2.getCheckoutDate(), "07-02-2020");
    }

    @Test
    public void RentalAgreement_CreateRentalAgreement3() throws Exception {
        tool3 = new Tool("CHNS", 5, 25, "07-02-2015");
        assertEquals(tool3.getToolType(), "Chainsaw");
        assertEquals(tool3.getToolBrand(), "Stihl");
        assertEquals(tool3.getDailyCharge(), BigDecimal.valueOf(1.49));
        assertEquals(tool3.isChargedOnWeekdays(), true);
        assertEquals(tool3.isChargedOnWeekends(), false);
        assertEquals(tool3.isChargedOnHolidays(), true);
        assertEquals(tool3.getToolCode(), "CHNS");
        assertEquals(tool3.getRentalDayCount(), 5);
        assertEquals(tool3.getDiscountPercentage(), 25);
        assertEquals(tool3.getCheckoutDate(), "07-02-2015");
    }

    @Test
    public void RentalAgreement_CreateRentalAgreement4() throws Exception {
        tool4 = new Tool("JAKD", 6, 0, "09-03-2015");
        assertEquals(tool4.getToolType(), "Jackhammer");
        assertEquals(tool4.getToolBrand(), "DeWalt");
        assertEquals(tool4.getDailyCharge(), BigDecimal.valueOf(2.99));
        assertEquals(tool4.isChargedOnWeekdays(), true);
        assertEquals(tool4.isChargedOnWeekends(), false);
        assertEquals(tool4.isChargedOnHolidays(), false);
        assertEquals(tool4.getToolCode(), "JAKD");
        assertEquals(tool4.getRentalDayCount(), 6);
        assertEquals(tool4.getDiscountPercentage(), 0);
        assertEquals(tool4.getCheckoutDate(), "09-03-2015");
    }

    @Test
    public void RentalAgreement_CreateRentalAgreement5() throws Exception {
        tool5 = new Tool("JAKR", 9, 0, "07-02-2015");
        assertEquals(tool5.getToolType(), "Jackhammer");
        assertEquals(tool5.getToolBrand(), "Ridgid");
        assertEquals(tool5.getDailyCharge(), BigDecimal.valueOf(2.99));
        assertEquals(tool5.isChargedOnWeekdays(), true);
        assertEquals(tool5.isChargedOnWeekends(), false);
        assertEquals(tool5.isChargedOnHolidays(), false);
        assertEquals(tool5.getToolCode(), "JAKR");
        assertEquals(tool5.getRentalDayCount(), 9);
        assertEquals(tool5.getDiscountPercentage(), 0);
        assertEquals(tool5.getCheckoutDate(), "07-02-2015");
    }

    @Test
    public void RentalAgreement_CreateRentalAgreement6() throws Exception {
        tool6 = new Tool("JAKR", 4, 50, "07-02-2020");
        assertEquals(tool6.getToolType(), "Jackhammer");
        assertEquals(tool6.getToolBrand(), "Ridgid");
        assertEquals(tool6.getDailyCharge(), BigDecimal.valueOf(2.99));
        assertEquals(tool6.isChargedOnWeekdays(), true);
        assertEquals(tool6.isChargedOnWeekends(), false);
        assertEquals(tool6.isChargedOnHolidays(), false);
        assertEquals(tool6.getToolCode(), "JAKR");
        assertEquals(tool6.getRentalDayCount(), 4);
        assertEquals(tool6.getDiscountPercentage(), 50);
        assertEquals(tool6.getCheckoutDate(), "07-02-2020");
    }
}
