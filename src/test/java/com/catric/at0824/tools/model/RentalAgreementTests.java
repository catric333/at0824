package com.catric.at0824.tools.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class RentalAgreementTests {
    @Autowired
    private Tool tool1;
    private Tool tool2;
    private Tool tool3;
    private Tool tool4;
    private Tool tool5;
    private Tool tool6;

    @Test
    public void RentalAgreement_CreateRentalAgreement1() throws Exception {
        try {
            tool1 = new Tool("JAKR", 5, 101, "09-03-2015");
            RentalAgreement r = new RentalAgreement(tool1);
            // Linter fix, unreachable. Above will always fail
            System.out.println(r.toString());
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Error: discountPercentage should be between 0-100%");
        }
    }

    @Test
    public void RentalAgreement_CreateRentalAgreement2() throws Exception {
        try {
            tool2 = new Tool("LADW", 3, 10, "07-02-2020");
            RentalAgreement r = new RentalAgreement(tool2);
            assertEquals(r.getToolType(), "Ladder");
            assertEquals(r.getToolBrand(), "Werner");
            assertEquals(r.getDueDate(), "07-05-2020");
            assertEquals(r.getNumChargeableDays(), 2);
            assertEquals(r.getDailyRentalCharge(), BigDecimal.valueOf(1.99));
            assertEquals(r.getPreDiscountCharge(), BigDecimal.valueOf(3.98));
            assertEquals(r.getDiscountAmount(), BigDecimal.valueOf(0.40).setScale(2, RoundingMode.HALF_UP));
            assertEquals(r.getFinalChargeAmount(), BigDecimal.valueOf(3.58));
        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void RentalAgreement_CreateRentalAgreement3() throws Exception {
        try {
            tool3 = new Tool("CHNS", 5, 25, "07-02-2015");
            RentalAgreement r = new RentalAgreement(tool3);
            assertEquals(r.getToolType(), "Chainsaw");
            assertEquals(r.getToolBrand(), "Stihl");
            assertEquals(r.getDueDate(), "07-07-2015");
            assertEquals(r.getNumChargeableDays(), 3);
            assertEquals(r.getDailyRentalCharge(), BigDecimal.valueOf(1.49));
            assertEquals(r.getPreDiscountCharge(), BigDecimal.valueOf(4.47));
            assertEquals(r.getDiscountAmount(), BigDecimal.valueOf(1.12));
            assertEquals(r.getFinalChargeAmount(), BigDecimal.valueOf(3.35));
        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void RentalAgreement_CreateRentalAgreement4() throws Exception {
        try {
            tool4 = new Tool("JAKD", 6, 0, "09-03-2015");
            RentalAgreement r = new RentalAgreement(tool4);
            assertEquals(r.getToolType(), "Jackhammer");
            assertEquals(r.getToolBrand(), "DeWalt");
            assertEquals(r.getDueDate(), "09-09-2015");
            assertEquals(r.getNumChargeableDays(), 3);
            assertEquals(r.getDailyRentalCharge(), BigDecimal.valueOf(2.99));
            assertEquals(r.getPreDiscountCharge(), BigDecimal.valueOf(8.97));
            assertEquals(r.getDiscountAmount(), BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP));
            assertEquals(r.getFinalChargeAmount(), BigDecimal.valueOf(8.97));
        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void RentalAgreement_CreateRentalAgreement5() throws Exception {
        try {
            tool5 = new Tool("JAKR", 9, 0, "07-02-2015");
            RentalAgreement r = new RentalAgreement(tool5);
            assertEquals(r.getToolType(), "Jackhammer");
            assertEquals(r.getToolBrand(), "Ridgid");
            assertEquals(r.getDueDate(), "07-11-2015");
            assertEquals(r.getNumChargeableDays(), 5);
            assertEquals(r.getDailyRentalCharge(), BigDecimal.valueOf(2.99));
            assertEquals(r.getPreDiscountCharge(), BigDecimal.valueOf(14.95));
            assertEquals(r.getDiscountAmount(), BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP));
            assertEquals(r.getFinalChargeAmount(), BigDecimal.valueOf(14.95));
        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }

    @Test
    public void RentalAgreement_CreateRentalAgreement6() throws Exception {
        try {
            tool6 = new Tool("JAKR", 4, 50, "07-02-2020");
            RentalAgreement r = new RentalAgreement(tool6);
            assertEquals(r.getToolType(), "Jackhammer");
            assertEquals(r.getToolBrand(), "Ridgid");
            assertEquals(r.getDueDate(), "07-06-2020");
            assertEquals(r.getNumChargeableDays(), 1);
            assertEquals(r.getDailyRentalCharge(), BigDecimal.valueOf(2.99));
            assertEquals(r.getPreDiscountCharge(), BigDecimal.valueOf(2.99));
            assertEquals(r.getDiscountAmount(), BigDecimal.valueOf(1.50).setScale(2, RoundingMode.HALF_UP));
            assertEquals(r.getFinalChargeAmount(), BigDecimal.valueOf(1.49));
        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }
}
