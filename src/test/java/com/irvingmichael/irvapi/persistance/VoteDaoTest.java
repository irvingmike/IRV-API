package com.irvingmichael.irv.persistance;

import com.irvingmichael.irv.entity.Vote;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aaron Anderson on 10/10/16.
 */
public class VoteDaoTest {
    @Test
    public void getVoteByVoterIdPollId() throws Exception {
        VoteDao voteDao = new VoteDao();
        Vote testVote = voteDao.getVoteByVoterIdPollId(2,1);

        assertEquals("Bad vote retrieval", (Integer) 2, testVote.getCurrentRankings().get(1));
        assertEquals("Bad vote retrieval", (Integer) 1, testVote.getCurrentRankings().get(2));
        assertEquals("Bad vote retrieval", (Integer) 3, testVote.getCurrentRankings().get(3));
        assertEquals("Bad vote retrieval", (Integer) 4, testVote.getCurrentRankings().get(4));

        assertEquals("Bad vote retrieval", (Integer) 2, testVote.getVoteRankings().get(1));
        assertEquals("Bad vote retrieval", (Integer) 1, testVote.getVoteRankings().get(2));
        assertEquals("Bad vote retrieval", (Integer) 3, testVote.getVoteRankings().get(3));
        assertEquals("Bad vote retrieval", (Integer) 4, testVote.getVoteRankings().get(4));
    }

}