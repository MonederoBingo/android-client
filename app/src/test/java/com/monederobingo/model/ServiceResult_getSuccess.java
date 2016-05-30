package com.monederobingo.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ServiceResult_getSuccess {

    @Test
    public void shouldBeTheSameAsConstructorParameter() {
        //given
        ServiceResult serviceResult = new ServiceResult(true, "", "");
        //when
        boolean success = serviceResult.isSuccess();
        //then
        assertTrue(success);
    }
}