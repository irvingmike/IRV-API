package com.irvingmichael.irvapi.entity;

import com.irvingmichael.irvapi.entity.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by aaron on 9/10/16.
 */
public class PollTest {

    private static Poll poll = new TestPollSetup().testPoll;
    private final Logger log = Logger.getLogger("debugLogger");

    @BeforeClass
    public static void setup() {
        poll = new TestPollSetup().testPoll;
    }
    @Test
    public void countVotes() throws Exception {
        poll.countVotes();
        for (Map.Entry<Integer, Integer> entry : poll.getVoteCounts().entrySet()) {
            log.debug("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        assertEquals("Bad count for choice A", (Integer) 3, poll.getVoteCounts().get(1));
        assertEquals("Bad count for choice B", (Integer) 4, poll.getVoteCounts().get(2));
        assertEquals("Bad count for choice C", (Integer) 2, poll.getVoteCounts().get(3));
        assertEquals("Bad count for choice D", (Integer) 1, poll.getVoteCounts().get(4));
    }

    @Before
    public void setVoteCountsToReset() {
        poll.setVoteCounts(new HashMap<Integer, Integer>() {{
            put(1, 1);
            put(2, 2);
            put(3, 3);
            put(4, 4);
        }});
    }
    @Test
    public void resetChoiceCounts() throws Exception {
        poll.setVotesCountsToZero();
        for (Map.Entry<Integer, Integer> entry : poll.getVoteCounts().entrySet()) {
            assertEquals("Vote count not se to zero", (Integer) 0, entry.getValue());
        }
    }
    @After
    public void emptyVoteCounts() {
        poll.setVotesCountsToZero();
    }

    @Test
    public void winnerExists() throws Exception {
        poll.setVoteCounts(new HashMap<Integer, Integer>() {{
            put(1, 1);
            put(2, 6);
            put(3, 2);
            put(4, 1);
        }});
        assertTrue(poll.winnerExists());
        poll.setVoteCounts(new HashMap<Integer, Integer>() {{
            put(1, 2);
            put(2, 4);
            put(3, 3);
            put(4, 1);
        }});
        assertTrue(!poll.winnerExists());
        poll.setVoteCounts(new HashMap<Integer, Integer>());
    }
    @After
    public void resetVoteCounts() {
        poll.setVoteCounts(new HashMap<Integer, Integer>());
    }

    @Test
    public void findHighestRankedChoice() throws Exception {
        Vote testVote = poll.getVotes().get(0);
        assertEquals("Wrong highest choice found", 1, poll.findHighestRankedChoice(testVote));
        /*testVote = poll.getVotes().get(1);
        assertEquals("Wrong highest choice found", 2, poll.findHighestRankedChoice(testVote));
        testVote = poll.getVotes().get(8);
        assertEquals("Wrong highest choice found", 3, poll.findHighestRankedChoice(testVote));*/
    }

    @Before
    public void createTestVoteCounts() {
        poll.setVoteCounts(new HashMap<Integer, Integer>() {{
            put(1, 3);
            put(2, 4);
            put(3, 2);
            put(4, 1);
        }});
    }
    @Test
    public void getLowestVoteGetter() throws Exception {
        poll.countVotes();
        assertEquals("Incorrect lowest voter getter returned", 4, poll.getLowestVoteGetter());
    }
    @After
    public void resetVoteCountToEmpty(){
        poll.setVoteCounts(new HashMap<Integer, Integer>());
    }

    @Test
    public void removeChoiceFromContention() throws Exception {
        ArrayList<Vote> originalVotes = poll.getVotes();
        ArrayList<Vote> modifiedVotes = new ArrayList<Vote>();
        int choiceCount = 0;

        modifiedVotes = poll.removeChoiceFromContention(1, originalVotes);
        for (Vote vote: modifiedVotes) {
            choiceCount += vote.getCurrentRankings().size();
        }
        assertEquals("Didn't remove the correct amount of votes", 30, choiceCount);
    }

    @Test
    public void removeChoiceFromVote() throws Exception {
        Vote testVote = poll.getVotes().get(0);
        int beforeLength = testVote.getCurrentRankings().size();
        Vote modifiedVote = poll.removeChoiceFromVote(1, testVote);
        assertEquals("Bad array length after removing vote", beforeLength - 1, modifiedVote.getCurrentRankings().size());
        assertEquals("Removed wrong choice", (Integer) 2, modifiedVote.getCurrentRankings().get(2));
        assertEquals("Removed wrong choice", (Integer) 3, modifiedVote.getCurrentRankings().get(3));
        assertEquals("Removed wrong choice", (Integer) 4, modifiedVote.getCurrentRankings().get(4));
    }

    @Test
    public void setVotesCountsToZero() throws Exception {
        poll.setVoteCounts(new HashMap<Integer, Integer>() {{
            put(1,1);
            put(2,2);
            put(3,3);
        }});
        poll.setVotesCountsToZero();
        assertEquals("Vote count 1 didn't set to zero", (Integer) 0, poll.getVoteCounts().get(1));
        assertEquals("Vote count 2 didn't set to zero", (Integer) 0, poll.getVoteCounts().get(2));
        assertEquals("Vote count 3 didn't set to zero", (Integer) 0, poll.getVoteCounts().get(3));
    }

    @Test
    public void getPollCode() throws Exception {
        Poll testPoll = new Poll();
        assertEquals("Poll code generated at incorrect size", 8, testPoll.getPollCode().length());
    }

    @Test
    public void getWinThreshold() throws Exception {
        Poll testPollTemp = new Poll("Win Threshold Test Poll");
        testPollTemp.setVotes(new ArrayList<Vote>());
        assertEquals("Empty vote array handled incorrectly", -1, testPollTemp.getWinThreshold());
        ArrayList<Vote> testVotes = new ArrayList<Vote>();
        testVotes.add(new Vote());
        testVotes.add(new Vote());
        testVotes.add(new Vote());
        testVotes.add(new Vote());
        testVotes.add(new Vote());
        testVotes.add(new Vote());
        testPollTemp.setVotes(testVotes);
        assertEquals("Bad win threshold for even number of votes", 3, testPollTemp.getWinThreshold());
        testVotes.add(new Vote());
        testPollTemp.setVotes(testVotes);
        assertEquals("Bad win threshold for odd number of votes", 3, testPollTemp.getWinThreshold());
    }

    @Test
    public void getChoiceNameById() throws Exception {
        assertEquals("Wrong name returned for choice 1", "Test Choice A", poll.getChoiceNameById(1));
        assertEquals("Wrong name returned for choice 2", "Test Choice B", poll.getChoiceNameById(2));

    }

    @Test
    public void pollTesting() {
        poll.determineWinner();

        // Comment out the last 4 testVotes in 'TestPollSetup' due to tied choices [C and D]
        assertEquals(2, poll.getWinner());
        assertEquals(0, poll.getPollid());

    }

}