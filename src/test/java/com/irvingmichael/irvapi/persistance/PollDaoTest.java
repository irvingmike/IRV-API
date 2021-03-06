package com.irvingmichael.irvapi.persistance;

import com.irvingmichael.irvapi.entity.*;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.HEAD;
import java.util.List;

import static com.irvingmichael.irvapi.entity.PollStatus.*;
import static org.junit.Assert.*;

/**
 * Created by Aaron Anderson on 10/26/16.
 */
public class PollDaoTest {

    @Before
    public void registerVoterForPollSetUp() {
        registerVoterForPollCleanUp();
    }
    @Test
    public void registerVoterForPoll() throws Exception {
        PollDao pollDao = new PollDao();
        assertFalse(pollDao.registerVoterForPoll("AAAAAAAA", 11));
        assertTrue(pollDao.registerVoterForPoll("abcdefgh", 11));
    }
    @After
    public void registerVoterForPollTearDown() {
        registerVoterForPollCleanUp();
    }

    @Test
    public void getAllPollsByVoterId() throws Exception {
        PollDao pollDao = new PollDao();
        List<Poll> polls = pollDao.getAllPollsByVoterId(1);

        assertEquals("Bad polls status", PollStatus.COMPLETED, polls.get(polls.size() - 1).getStatus());
        assertTrue(polls.size() > 0);
        assertEquals("Bad choices size for oldest poll", 1, polls.get(polls.size() - 1).getWinner());
        assertEquals("Bad poll name returned for oldest poll", "Test Poll", polls.get(polls.size() - 1).getTitle());
    }

    // Here are test helper methods
    public void registerVoterForPollCleanUp() {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        SQLQuery sql  = session.createSQLQuery("DELETE FROM VotersPolls WHERE voterid=11 AND pollid=1");
        sql.executeUpdate();
        tx.commit();
    }

    //  Tests for any changes in a specific poll
    @Test
    public void updatePollTest() {
        PollDao testDao = new PollDao();
        Poll testPoll = new TestPollSetup().testPoll;

        testPoll.completePoll();

        testDao.update(testPoll);
    }
}