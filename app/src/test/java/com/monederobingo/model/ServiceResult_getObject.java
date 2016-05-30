package com.monederobingo.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ServiceResult_getObject {

    @Test
    public void shouldBeTheSameAsConstructorParameter() {
        //given
        ServiceResult serviceResult = new ServiceResult(false, "", "object");
        //when
        String object = serviceResult.getObject();
        //then
        assertEquals("object", object);
    }
}