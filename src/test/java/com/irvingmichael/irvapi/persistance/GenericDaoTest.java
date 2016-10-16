package com.irvingmichael.irv.persistance;

import com.irvingmichael.irv.entity.Choice;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Aaron Anderson on 10/6/16.
 */
public class GenericDaoTest {
    @Test
    public void testCreate() throws Exception {
        GenericDao<Choice> choiceDao = new GenericDao<>(Choice.class);
        Choice testChoice = new Choice("testChoice", 1);
        testChoice.setId(choiceDao.create(testChoice));
        assertTrue(testChoice.getId() > 1);
        choiceDao.delete(testChoice);
    }

    @Test
    public void testGetAll() throws Exception {
        GenericDao<Choice> choiceDao = new GenericDao<>(Choice.class);
        List<Choice> choices;
        choices = choiceDao.getAll();
        assertEquals("Incorrect number of choices returned", 4, choices.size());
    }

    @Test
    public void testGetById() throws Exception {
        GenericDao<Choice> choiceDao = new GenericDao<>(Choice.class);
        assertEquals("Bad choice returned", "Choice A", choiceDao.getById(1).getName());
        assertEquals("Bad choice returned", "Choice B", choiceDao.getById(2).getName());
        assertEquals("Bad choice returned", "Choice C", choiceDao.getById(3).getName());
    }

    @Test
    public void testUpdate() throws Exception {
        GenericDao<Choice> choiceDao = new GenericDao<>(Choice.class);
        Choice testChoice = new Choice("Test Choice", 1);
        testChoice.setId(choiceDao.create(testChoice));
        testChoice.setName("Renamed Choice");
        choiceDao.update(testChoice);
        Choice confirmChoice = choiceDao.getById(testChoice.getId());
        assertEquals("Bad name update on Choice", "Renamed Choice", confirmChoice.getName());
        choiceDao.delete(testChoice);
    }

    @Test
    public void testDelete() throws Exception {
        GenericDao<Choice> choiceDao = new GenericDao<>(Choice.class);
        Choice testChoice = new Choice("Test Choice", 1);
        int testId = choiceDao.create(testChoice);
        testChoice = choiceDao.getById(1);
        testChoice = choiceDao.getById(testId);
        choiceDao.delete(testChoice);
        assertNull(choiceDao.getById(testId));
    }

}