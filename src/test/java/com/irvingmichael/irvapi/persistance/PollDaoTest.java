package com.irvingmichael.irvapi.persistance;

import com.irvingmichael.irvapi.entity.Poll;
import com.irvingmichael.irvapi.entity.PollStatus;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Aaron Anderson on 10/26/16.
 */
public class PollDaoTest {
    @Test
    public void getAllPollsByVoterId() throws Exception {
        PollDao pollDao = new PollDao();
        List<Poll> polls = pollDao.getAllPollsByVoterId(1);
        assertEquals("Bad polls status", PollStatus.INITIAL, polls.get(polls.size() - 1).getStatus());
        assertEquals("Bad list size returned for getAllPollsByVoterId", 1, polls.size());
        assertEquals("Bad choices size for oldest poll", -1, polls.get(polls.size() - 1).getWinner());
        assertEquals("Bad poll name returned for oldest poll", "Test Poll", polls.get(polls.size() - 1).getTitle());
    }

}