package com.goit.startup.entity;

import com.goit.startup.enums.UserRole;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Вадим on 23.05.2017.
 */
public class UserTest {

    @Test
    public void toStringTest() throws Exception {

        User testUser = new User("name", "pass", UserRole.ADMIN);
        String toString = testUser.toString();
        assertTrue(toString.contains("name") &&
                toString.contains("pass") &&
                toString.contains("ADMIN"));
    }

    @Test
    public void equals() throws Exception {

        User testUser1 = new User("name", "pass", UserRole.ADMIN);
        User testUser2 = new User("name", "pass", UserRole.ADMIN);
        User testUser3 = new User("name", "pass", UserRole.ADMIN);
        //reflexive
        assertEquals(testUser1,testUser1);
        //symmetric
        assertEquals(testUser1,testUser2);
        assertEquals(testUser2,testUser1);
        //transitive
        assertEquals(testUser1,testUser2);
        assertEquals(testUser2,testUser3);
        assertEquals(testUser1,testUser3);


        testUser1.setLocked(true);
        assertNotEquals(testUser1, testUser2);
        testUser2.setLocked(true);
        assertEquals(testUser1, testUser2);

        testUser1.setUsername("newName");
        assertNotEquals(testUser1, testUser2);
        testUser2.setUsername("newName");
        assertEquals(testUser1, testUser2);

        testUser1.setPassword("newPass");
        assertNotEquals(testUser1, testUser2);
        testUser2.setPassword("newPass");
        assertEquals(testUser1, testUser2);

        testUser1.setPassword(null);
        assertNotEquals(testUser1, testUser2);
        testUser2.setPassword(null);
        assertEquals(testUser1, testUser2);

        testUser1.setContacts("contacts");
        assertNotEquals(testUser1, testUser2);
        testUser2.setContacts("contacts");
        assertEquals(testUser1, testUser2);

        testUser1.setRole(UserRole.USER);
        assertNotEquals(testUser1, testUser2);
        assertNotEquals(testUser1, null);
        assertNotEquals(testUser1, new Integer(5));
    }

    @Test
    public void hashCodeTest() throws Exception {

        User testUser1 = new User("name", "pass", UserRole.ADMIN);
        User testUser2 = new User("name", "pass", UserRole.ADMIN);
        assertTrue(testUser1.hashCode() == testUser2.hashCode());

        testUser1.setLocked(true);
        assertFalse(testUser1.hashCode() == testUser2.hashCode());
        testUser2.setLocked(true);

        testUser1.setPassword("pass1");
        assertFalse(testUser1.hashCode() == testUser2.hashCode());

        testUser1.setPassword(null);
        assertFalse(testUser1.hashCode() == testUser2.hashCode());
    }

    @Test
    public void isAccountNonExpired() throws Exception {

        User testUser = new User("name", "pass", UserRole.USER);

        testUser.setLocked(false);
        assertTrue(testUser.isAccountNonExpired());

        testUser.setLocked(true);
        assertFalse(testUser.isAccountNonExpired());
    }

    @Test
    public void isAccountNonLocked() throws Exception {
        User testUser = new User();
        testUser.setLocked(false);
        assertTrue(testUser.isAccountNonLocked());
        testUser.setLocked(true);
        assertFalse(testUser.isAccountNonLocked());
    }

    @Test
    public void isCredentialsNonExpired() throws Exception {
        User testUser = new User();
        testUser.setLocked(false);
        assertTrue(testUser.isCredentialsNonExpired());
        testUser.setLocked(true);
        assertFalse(testUser.isCredentialsNonExpired());
    }

    @Test
    public void isEnabled() throws Exception {
        User testUser = new User();
        testUser.setLocked(false);
        assertTrue(testUser.isEnabled());
        testUser.setLocked(true);
        assertFalse(testUser.isEnabled());
    }

    @Test
    public void getAuthorities() throws Exception {
        User testUser = new User("name", "pass", UserRole.USER);
        Collection<GrantedAuthority> grantedAuthorities = testUser.getAuthorities();
        assertNotNull(grantedAuthorities);
        assertTrue(grantedAuthorities.size() == 1);
    }

    @Test
    public void getAndSetUsername() throws Exception {
        User testUser = new User();
        assertEquals(testUser.getUsername(), "");
        testUser.setUsername(null);
        assertEquals(testUser.getUsername(), "");
        testUser.setUsername("     ");
        assertEquals(testUser.getUsername(), "");
        testUser.setUsername("newName");
        assertEquals(testUser.getUsername(), "newName");
    }

    @Test
    public void getAndGetConfirmPassword() throws Exception {
        User testUser = new User();
        testUser.setConfirmPassword("newPass");
        assertEquals(testUser.getConfirmPassword(), "newPass");
    }


    @Test
    public void getAndSetContacts() throws Exception {
        User testUser = new User();
        assertEquals(testUser.getContacts(), "");
        testUser.setContacts(null);
        assertEquals(testUser.getContacts(), "");
        testUser.setContacts("     ");
        assertEquals(testUser.getContacts(), "");
        testUser.setContacts("test@test.ua");
        assertEquals(testUser.getContacts(), "test@test.ua");
    }

    @Test
    public void getAndSetPassword() throws Exception {
        User testUser = new User();
        assertEquals(testUser.getPassword(), "");
        testUser.setPassword("newPass");
        assertEquals(testUser.getPassword(), "newPass");
    }

    @Test
    public void getAndSetRole() throws Exception {
        User testUser = new User();
        assertEquals(testUser.getRole(), UserRole.USER);
        testUser.setRole(null);
        assertEquals(testUser.getRole(), UserRole.USER);
        testUser.setRole(UserRole.ADMIN);
        assertTrue(testUser.getRole().equals(UserRole.ADMIN));
    }

    @Test
    public void isLockedAndSetLocked() throws Exception {
        User testUser = new User();
        testUser.setLocked(true);
        assertTrue(testUser.isLocked());
        testUser.setLocked(false);
        assertFalse(testUser.isLocked());
    }

    @Test
    public void getAndSetStartups() throws Exception {
        Startup startup1 = new Startup();
        startup1.setName("startup1");
        Startup startup2 = new Startup();
        startup2.setName("startup2");
        Set<Startup> startups = new HashSet<>();
        startups.add(startup1);
        startups.add(startup2);

        User testUser = new User();
        assertTrue(testUser.getStartups().size()==0);

        testUser.setStartups(startups);
        assertTrue(testUser.getStartups().size()==2);
        assertTrue(testUser.getStartups().contains(startup1)&&testUser.getStartups().contains(startup2));

        testUser.setStartups(null);
        assertTrue(testUser.getStartups().isEmpty());
    }


    @Test
    public void getAndSetInvestments() throws Exception {
        Investment investment = new Investment();
        investment.setAmount(100);
        Set<Investment> investments = new HashSet<>();
        investments.add(investment);
        User testUser = new User();
        assertTrue(testUser.getInvestments().size()==0);
        testUser.setInvestments(investments);
        assertTrue(testUser.getInvestments().contains(investment));
        testUser.setInvestments(null);
        assertTrue(testUser.getInvestments().isEmpty());
    }


    @Test
    public void getAndSetImageId() throws Exception {
        User testUser = new User();
        assertEquals(testUser.getImageId(), 1L);
        testUser.setImageId(50L);
        assertEquals(testUser.getImageId(), 50L);
    }

}