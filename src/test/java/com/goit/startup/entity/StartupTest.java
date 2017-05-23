package com.goit.startup.entity;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Вадим on 23.05.2017.
 */
public class StartupTest {



    @Test
    public void toStringTest() throws Exception {
        Startup startup = new Startup();
        startup.setName("Test");
        startup.setDescription("description");
        String toString = startup.toString();
        assertTrue(toString.contains("Test")
                &&toString.contains("description"));

    }

    @Test
    public void equals() throws Exception {

        Startup testStartup1 = new Startup();
        Startup testStartup2 = new Startup();
        Startup testStartup3 = new Startup();

        //reflexive
        assertEquals(testStartup1,testStartup1);
        //symmetric
        assertEquals(testStartup1,testStartup2);
        assertEquals(testStartup2,testStartup1);
        //transitive
        assertEquals(testStartup1,testStartup2);
        assertEquals(testStartup2,testStartup3);
        assertEquals(testStartup1,testStartup3);

        assertNotEquals(testStartup1, null);
        assertNotEquals(testStartup1, new Integer(5));


        testStartup1.setMinInvestment(10);
        assertNotEquals(testStartup1, testStartup2);
        testStartup2.setMinInvestment(10);
        assertEquals(testStartup1, testStartup2);


        testStartup1.setNeedInvestment(1000);
        assertNotEquals(testStartup1, testStartup2);
        testStartup2.setNeedInvestment(1000);
        assertEquals(testStartup1, testStartup2);

        testStartup1.setImageId(2L);
        assertNotEquals(testStartup1, testStartup2);
        testStartup2.setImageId(2L);
        assertEquals(testStartup1, testStartup2);

        testStartup1.setName("test");
        assertNotEquals(testStartup1, testStartup2);
        testStartup2.setName("test");
        assertEquals(testStartup1, testStartup2);

        testStartup1.setDescription("test");
        assertNotEquals(testStartup1, testStartup2);
        testStartup2.setDescription("test");
        assertEquals(testStartup1, testStartup2);

        User user = new User();
        user.setUsername("TestUser");
        testStartup1.setAuthor(user);
        assertNotEquals(testStartup1, testStartup2);
        testStartup2.setAuthor(user);
        assertEquals(testStartup1, testStartup2);
        testStartup1.setAuthor(null);
        assertNotEquals(testStartup1, testStartup2);

    }

    @Test
    public void hashCodeTest() throws Exception {
        Startup startup1 = new Startup();
        Startup startup2 = new Startup();
        assertTrue(startup1.hashCode() == startup2.hashCode());

        startup1.setDescription("test");
        assertFalse(startup1.hashCode() == startup2.hashCode());
        startup2.setDescription("test");

        startup1.setMinInvestment(1);
        assertFalse(startup1.hashCode() == startup2.hashCode());
        startup2.setMinInvestment(1);

        startup1.setNeedInvestment(20);
        assertFalse(startup1.hashCode() == startup2.hashCode());
        startup2.setNeedInvestment(20);

        startup1.setImageId(2L);
        assertFalse(startup1.hashCode() == startup2.hashCode());
        startup2.setImageId(2L);

        startup1.setAuthor(null);
        assertTrue(startup1.hashCode() == startup2.hashCode());
        startup1.setAuthor(new User());
        assertFalse(startup1.hashCode() == startup2.hashCode());
        startup2.setAuthor(new User());
        assertTrue(startup1.hashCode() == startup2.hashCode());
    }

    @Test
    public void getAndSetInvestmentsAndGetCurrentInvestments() throws Exception {
        Investment investment1 = new Investment();
        Investment investment2 = new Investment();
        investment1.setAmount(100);
        investment2.setAmount(50);
        Set<Investment> investments = new HashSet<>();
        investments.add(investment1);

        Startup startup = new Startup();

        assertTrue(startup.getInvestments().isEmpty());
        assertNotNull(startup.getInvestments());

        startup.setInvestments(investments);
        assertTrue(startup.getCurrentInvestments()==100);
        assertTrue(startup.getInvestments().size() == 1);

        startup.setInvestments(null);
        assertNotNull(startup.getInvestments());

        startup.makeInvestment(investment2);
        startup.makeInvestment(investment1);
        assertTrue(startup.getInvestments().size() == 2);
        assertTrue(startup.getInvestments().contains(investment1)
        && startup.getInvestments().contains(investment2));




    }

    @Test
    public void getAndSetName() throws Exception {
        Startup startup = new Startup();
        startup.setName("Test");
        assertEquals("Test",startup.getName());
        startup.setName(null);
        assertTrue(startup.getName().equals(""));
    }


    @Test
    public void getAndSetDescription() throws Exception {

        Startup startup = new Startup();
        startup.setDescription("Test");
        assertEquals("Test",startup.getDescription());
        startup.setDescription(null);
        assertTrue(startup.getDescription().equals(""));
    }



    @Test
    public void getAndSetMinInvestment() throws Exception {

        Startup startup = new Startup();
        startup.setMinInvestment(15);
        assertTrue(startup.getMinInvestment()==15);
        startup.setMinInvestment(-10);
        assertTrue(startup.getMinInvestment()==0);
    }


    @Test
    public void getAndSetNeedInvestment() throws Exception {

        Startup startup = new Startup();
        startup.setNeedInvestment(100);
        assertTrue(startup.getNeedInvestment()==100);
        startup.setNeedInvestment(-10);
        assertTrue(startup.getNeedInvestment()==0);
    }


    @Test
    public void getaAndSetAuthor() throws Exception {
        Startup startup = new Startup();
        User user = new User();
        user.setUsername("TestUser");
        startup.setAuthor(user);
        assertEquals(startup.getAuthor(),user);
    }


    @Test
    public void getAndSetImageId() throws Exception {

        Startup startup = new Startup();
        assertEquals(startup.getImageId(), 1L);
        startup.setImageId(50L);
        assertEquals(startup.getImageId(), 50L);
    }


}