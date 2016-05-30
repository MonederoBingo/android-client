package com.monederobingo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompanyTest_getName {

    @Test
    public void shouldBeTheSameAsConstructorParameter() {
        //given
        Company company = new Company(1, "name", "", 0.0);
        //when
        String name = company.getName();
        //then
        assertEquals("name", name);
    }
}