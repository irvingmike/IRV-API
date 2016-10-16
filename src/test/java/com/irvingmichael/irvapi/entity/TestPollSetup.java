package com.irvingmichael.irv.entity;

import com.irvingmichael.irv.entity.Choice;
import com.irvingmichael.irv.entity.Poll;
import com.irvingmichael.irv.entity.Vote;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by aaron on 9/14/16.
 */
public class TestPollSetup {

    Poll testPoll;

    private final Logger logger = Logger.getLogger(this.getClass());


    TestPollSetup() {
        testPoll = new Poll("Test Poll");
        testPoll.setChoices(makeTestChoices());
        testPoll.setVotes(makeTestVotes());
    }

    private ArrayList<Choice> makeTestChoices() {
        ArrayList<Choice> testChoices = new ArrayList<Choice>();
        testChoices.add(new Choice(1, "Test Choice A"));
        testChoices.add(new Choice(2, "Test Choice B"));
        testChoices.add(new Choice(3, "Test Choice C"));
        testChoices.add(new Choice(4, "Test Choice D"));
        return testChoices;
    }

    private ArrayList<Vote> makeTestVotes() {
        ArrayList<Vote> testVotes = new ArrayList<Vote>();

        Vote testVote = new Vote();
        int[] rankings = {1,2,3,4};
        testVote.setVoteRankings(createRanking(rankings));
        testVote.setCurrentRankings(createRanking(rankings));
        testVotes.add(testVote);

        testVote = new Vote();
        rankings = new int[] {2,1,3,4};
        testVote.setVoteRankings(createRanking(rankings));
        testVote.setCurrentRankings(createRanking(rankings));
        testVotes.add(testVote);

        testVote = new Vote();
        rankings = new int[] {2,1,3,4};
        testVote.setVoteRankings(createRanking(rankings));
        testVote.setCurrentRankings(createRanking(rankings));
        testVotes.add(testVote);

        testVote = new Vote();
        rankings = new int[] {2,1,3,4};
        testVote.setVoteRankings(createRanking(rankings));
        testVote.setCurrentRankings(createRanking(rankings));
        testVotes.add(testVote);

        testVote = new Vote();
        rankings = new int[] {3,4,1,2};
        testVote.setVoteRankings(createRanking(rankings));
        testVote.setCurrentRankings(createRanking(rankings));
        testVotes.add(testVote);

        testVote = new Vote();
        rankings = new int[] {2,3,4,1};
        testVote.setVoteRankings(createRanking(rankings));
        testVote.setCurrentRankings(createRanking(rankings));
        testVotes.add(testVote);

        testVote = new Vote();
        rankings = new int[] {1,2,3,4};
        testVote.setVoteRankings(createRanking(rankings));
        testVote.setCurrentRankings(createRanking(rankings));
        testVotes.add(testVote);

        testVote = new Vote();
        rankings = new int[] {4,1,3,2};
        testVote.setVoteRankings(createRanking(rankings));
        testVote.setCurrentRankings(createRanking(rankings));
        testVotes.add(testVote);

        testVote = new Vote();
        rankings = new int[] {2,3,1,4};
        testVote.setVoteRankings(createRanking(rankings));
        testVote.setCurrentRankings(createRanking(rankings));
        testVotes.add(testVote);

        testVote = new Vote();
        rankings = new int[] {1,2,3,4};
        testVote.setVoteRankings(createRanking(rankings));
        testVote.setCurrentRankings(createRanking(rankings));
        testVotes.add(testVote);

        return testVotes;
    }

    private LinkedHashMap<Integer, Integer> createRanking(int[] rankings) {
        LinkedHashMap<Integer, Integer> testRankings = new LinkedHashMap<Integer, Integer>();
        for (int i = 0; i < 4; i++) {
            testRankings.put((i+1), rankings[i]);
        }
        return testRankings;
    }
}
