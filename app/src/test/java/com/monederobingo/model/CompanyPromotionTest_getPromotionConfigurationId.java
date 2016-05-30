package com.monederobingo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompanyPromotionTest_getPromotionConfigurationId {

    @Test
    public void shouldBeTheSameAsConstructorParameter() {
        //given
        CompanyPromotion companyPromotion = new CompanyPromotion(1, 0, "", 0.0);
        //when
        long promotionConfigurationId = companyPromotion.getPromotionConfigurationId();
        //then
        assertEquals(1, promotionConfigurationId);
    }
}