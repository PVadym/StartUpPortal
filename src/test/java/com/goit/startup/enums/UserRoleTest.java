package com.goit.startup.enums;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Вадим on 23.05.2017.
 */
public class UserRoleTest {

    @Test
    public void valuesTest(){
        String toString = Arrays.toString(UserRole.values());
        assertTrue(toString.contains("user".toUpperCase())
        &&toString.contains("admin".toUpperCase()));
    }

    @Test
    public void valueOfStringTest(){
        assertEquals(UserRole.USER, UserRole.valueOf("USER"));
        assertEquals(UserRole.ADMIN, UserRole.valueOf("ADMIN"));
    }

}