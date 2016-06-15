package com.monederobingo.app;

import com.android.volley.toolbox.ImageLoader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static com.monederobingo.TestVerifiers.call;
import static com.monederobingo.TestVerifiers.neverCall;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_getImageLoader extends AppControllerSpec {

    @Test
    public void givenImageLoaderIsNull() throws NoSuchFieldException, IllegalAccessException {
        //given
        Field imageLoaderField = getImageLoaderField();
        imageLoaderField.set(appController, null);
        doReturn(imageLoader).when(appController).createImageLoader();
        //when
        ImageLoader actualImageLoader = appController.getImageLoader();
        //then
        call(appController).createImageLoader();
        assertEquals(imageLoader, actualImageLoader);
    }

    @Test
    public void givenImageLoaderIsNotNull() throws NoSuchFieldException, IllegalAccessException {
        //given
        Field imageLoaderField = getImageLoaderField();
        imageLoaderField.set(appController, imageLoader);
        //when
        ImageLoader actualImageLoader = appController.getImageLoader();
        //then
        neverCall(appController).createImageLoader();
        assertEquals(imageLoaderField.get(appController), actualImageLoader);
    }
}