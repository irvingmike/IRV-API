package com.irvingmichael.irv.persistance;

import com.irvingmichael.irv.entity.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Aaron Anderson
 */
public class ChoiceDaoTest {

    @Test
    public void getAllChoicesForPoll() throws Exception {
        ChoiceDao choice001 = new ChoiceDao();
        assertEquals("Wrong number of choices returned", 4, choice001.getAllChoicesForPoll(1).size());
    }

    @Test
    public void testGetAll() throws Exception {
        ChoiceDao choiceDao = new ChoiceDao();
        List<Choice> choices = choiceDao.getAll();
        assertEquals(4, choices.size());
    }

}