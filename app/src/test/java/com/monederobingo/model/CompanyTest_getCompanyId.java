package com.monederobingo.model;

import org.junit.Test;

import java.util.SortedMap;

import static org.junit.Assert.*;

public class CompanyTest_getCompanyId {

    @Test
    public void shouldBeTheSameAsConstructorParameter() {
        //given
        Company company = new Company(1, "", "", 0.0);
        //when
        int companyId = company.getCompanyId();
        //then
        assertEquals(1, companyId);
    }
}