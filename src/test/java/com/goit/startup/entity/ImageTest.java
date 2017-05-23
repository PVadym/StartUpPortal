package com.goit.startup.entity;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Вадим on 23.05.2017.
 */
public class ImageTest {

    private Image image1;
    private Image image2;

    public ImageTest() {
        this.image1 = new Image();
        this.image2 = new Image();
        image1.setData(new byte[]{1,2,3,4,5});
        image2.setData(new byte[]{1,2,3,4,5});
    }

    @Test
    public void equals() throws Exception {

        assertEquals(image1,image2);
        image1.setData(new byte[]{0,4,5});

        assertNotEquals(image1,image2);
        assertNotEquals(image1, null);
        assertNotEquals(image1, new Integer(5));
    }

    @Test
    public void hashCodeTest() throws Exception {

        assertTrue(image1.hashCode()==image2.hashCode());

    }

    @Test
    public void toStringTest() throws Exception {
        Image image = new Image();
        image.setId(5L);
        assertTrue(image.toString().contains("5"));
    }

    @Test
    public  void getAndSetData() throws Exception {

        byte [] array = {1,2,3,4,5};
        assertTrue(Arrays.equals(image1.getData(),array));
    }


}