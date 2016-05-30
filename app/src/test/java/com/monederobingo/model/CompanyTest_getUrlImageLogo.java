package com.monederobingo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompanyTest_getUrlImageLogo {

    @Test
    public void shouldBeTheSameAsConstructorParameter() {
        //given
        Company company = new Company(1, "", "url", 0.0);
        //when
        String url = company.getUrlImageLogo();
        //then
        assertEquals("url", url);
    }
}