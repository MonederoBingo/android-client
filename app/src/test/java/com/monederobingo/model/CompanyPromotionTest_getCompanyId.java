package com.monederobingo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompanyPromotionTest_getCompanyId {

    @Test
    public void shouldBeTheSameAsConstructorParameter() {
        //given
        CompanyPromotion companyPromotion = new CompanyPromotion(0, 1, "", 0.0);
        //when
        long companyId = companyPromotion.getCompanyId();
        //then
        assertEquals(1, companyId);
    }
}