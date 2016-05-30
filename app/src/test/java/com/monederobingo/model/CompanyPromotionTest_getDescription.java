package com.monederobingo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompanyPromotionTest_getDescription {

    @Test
    public void shouldBeTheSameAsConstructorParameter() {
        //given
        CompanyPromotion companyPromotion = new CompanyPromotion(0, 0, "description", 0.0);
        //when
        String description = companyPromotion.getDescription();
        //then
        assertEquals("description", description);
    }
}