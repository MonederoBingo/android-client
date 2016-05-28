package com.monederobingo.common;

import android.content.Context;
import android.content.Intent;

import com.monederobingo.BaseUnitTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class ActivityUtilTest_exit extends BaseUnitTest {

    @Mock
    private Context context;
    @Mock
    private Intent intent;
    @InjectMocks
    private ActivityUtil activityUtil;

    @Before
    public void setUp() {
        this.activityUtil = new ActivityUtil(context);
        this.activityUtil.setIntent(intent);
    }

    @Test
    public void shouldSetFlags() {
        //when
        activityUtil.exit();
        //then
        verify(intent).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Test
    public void shouldPutExtra() {
        //when
        activityUtil.exit();
        //then
        verify(intent).putExtra("EXIT", true);
    }

    @Test
    public void shouldStartActivity() {
        //when
        activityUtil.exit();
        //then
        verify(context).startActivity(intent);
    }
}