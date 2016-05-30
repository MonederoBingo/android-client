package com.monederobingo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompanyPromotionTest_getRequiredPoints {

    @Test
    public void shouldBeTheSameAsConstructorParameter() {
        //given
        CompanyPromotion companyPromotion = new CompanyPromotion(0, 0, "", 1.0);
        //when
        double requiredPoints = companyPromotion.getRequiredPoints();
        //then
        assertEquals(1.0, requiredPoints, 0.00);
    }
}