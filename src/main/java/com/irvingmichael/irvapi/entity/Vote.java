package com.irvingmichael.irvapi.entity;

import javax.persistence.*;
import java.util.LinkedHashMap;

/**
 * Vote class, holds all the information for a single vote for a voter in a poll
 *
 * @author Aaron Anderson
 */

@Entity
@Table(name = "Votes")
public class Vote {

    @Id
    @Column(name = "voteid")
    private int voteId;

    @Column(name = "pollid")
    private int pollId;

    @Column(name = "voterid")
    private int voterId;

    @Transient
    private LinkedHashMap<Integer, Integer> voteRankings;

    @Transient
    private LinkedHashMap<Integer, Integer> currentRankings;

    /**
     * Empty constructor
     */
    public Vote() {
        voteRankings = new LinkedHashMap<Integer, Integer>();
        currentRankings = new LinkedHashMap<Integer, Integer>();
    }

    /**
     * Main constructor for Vote
     * @param voterId VoterId of voter that cast vote
     * @param pollId PollId for the poll Vote is cast in
     */
    public Vote(int voterId, int pollId) {
        this();
        this.voterId = voterId;
        this.pollId = pollId;
    }

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    public LinkedHashMap<Integer, Integer> getVoteRankings() {
        return voteRankings;
    }

    public void setVoteRankings(LinkedHashMap<Integer, Integer> voteRankings) {
        this.voteRankings = voteRankings;
    }

    public LinkedHashMap<Integer, Integer> getCurrentRankings() {
        return currentRankings;
    }

    public void setCurrentRankings(LinkedHashMap<Integer, Integer> currentRankings) {
        this.currentRankings = currentRankings;
    }
}
