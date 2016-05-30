package com.monederobingo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompanyTest_getPoints {

    @Test
    public void shouldBeTheSameAsConstructorParameter() {
        //given
        Company company = new Company(1, "", "", 1.0);
        //when
        double points = company.getPoints();
        //then
        assertEquals(1.0, points, 0.00);
    }
}