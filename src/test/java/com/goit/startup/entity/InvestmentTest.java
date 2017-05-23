package com.goit.startup.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Вадим on 23.05.2017.
 */
public class InvestmentTest {

    @Test
    public void toStringTest() throws Exception {
        Investment investment = new Investment();
        investment.setAmount(150);
        assertTrue(investment.toString().contains("150"));
    }

    @Test
    public void equalsTest() throws Exception {

        Investment investment1 = new Investment();
        Investment investment2 = new Investment();
        Investment investment3 = new Investment();

        //reflexive
        assertEquals(investment1,investment1);
        //symmetric
        assertEquals(investment1,investment2);
        assertEquals(investment2,investment1);
        //transitive
        assertEquals(investment1,investment2);
        assertEquals(investment2,investment3);
        assertEquals(investment1,investment3);

        assertNotEquals(investment1, null);
        assertNotEquals(investment1, new Integer(5));

        investment1.setId(1L);
        assertNotEquals(investment1,investment2);
        investment2.setId(1L);
        assertEquals(investment1,investment2);

        investment1.setAmount(1000);
        assertNotEquals(investment1,investment2);
        investment2.setAmount(1000);
        assertEquals(investment1,investment2);

        investment1.setInvestor(null);
        assertEquals(investment1,investment2);
        investment2.setInvestor(null);
        assertEquals(investment1,investment2);

        User user1 = new User();
        User user2 = new User();
        investment2.setInvestor(user2);
        assertNotEquals(investment1,investment2);

        investment1.setInvestor(user1);
        assertEquals(investment1,investment2);

        user1.setUsername("Test");
        assertNotEquals(investment1,investment2);
        user2.setUsername("Test");

        investment1.setStartup(null);
        assertEquals(investment1,investment2);
        investment2.setStartup(new Startup());
        assertNotEquals(investment1,investment2);
        investment1.setStartup(new Startup());
        assertEquals(investment1,investment2);
    }

    @Test
    public void hashCodeTest() throws Exception {
        Investment investment1 = new Investment();
        Investment investment2 = new Investment();

        assertTrue(investment1.hashCode() == investment2.hashCode());

        investment1.setId(1L);
        assertFalse(investment1.hashCode() == investment2.hashCode());
        investment2.setId(1L);

        investment1.setAmount(1000);
        assertFalse(investment1.hashCode() == investment2.hashCode());
        investment2.setAmount(1000);

        investment1.setInvestor(null);
        assertTrue(investment1.hashCode() == investment2.hashCode());
        investment2.setInvestor(new User());
        assertFalse(investment1.hashCode() == investment2.hashCode());
        investment1.setInvestor(new User());
        assertTrue(investment2.hashCode() == investment1.hashCode());

        investment1.setStartup(null);
        assertTrue(investment1.hashCode() == investment2.hashCode());
        investment2.setStartup(new Startup());
        assertFalse(investment1.hashCode() == investment2.hashCode());
        investment1.setStartup(new Startup());
        assertTrue(investment2.hashCode() == investment1.hashCode());


    }

    @Test
    public void getAndSetAmount() throws Exception {

        Investment investment = new Investment();
        investment.setAmount(150);
        assertTrue(investment.getAmount()==150);
        investment.setAmount(-10);
        assertTrue(investment.getAmount()==0);
    }


    @Test
    public void getAndSetInvestor() throws Exception {
        Investment investment = new Investment();
        User user = new User();
        user.setUsername("TestUser");
        investment.setInvestor(user);
        assertEquals(investment.getInvestor(),user);
    }

    @Test
    public void getAndSetStartup() throws Exception {
        Investment investment = new Investment();
        Startup startup = new Startup();
        startup.setName("Test");
        investment.setStartup(startup);
        assertEquals(investment.getStartup(),startup);
    }


}