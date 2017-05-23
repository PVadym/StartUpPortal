package com.goit.startup.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Вадим on 23.05.2017.
 */
public class ModelTest {

    private class AbstractModel extends Model{

        @Override
        public int hashCode() {
            return 0;
        }
    }

    Model model = new AbstractModel();

    @Test
    public void equals() throws Exception {

        Model model2 = new AbstractModel();
        Model model3 = new AbstractModel();
        assertEquals(model,model);
        assertEquals(model,model2);
        assertEquals(model2, model);
        assertEquals(model2, model3);
        assertEquals(model3, model);
        assertFalse(model.equals(null));
        assertFalse(model.equals(new Integer(5)));

    }

    @Test
    public void toStringTest() throws Exception {
        model.setId(1);
        assertTrue(model.toString().contains("1"));
    }

    @Test
    public void getIdAndGetId() throws Exception {
        model.setId(111);
        assertEquals(111, model.getId());
    }

}