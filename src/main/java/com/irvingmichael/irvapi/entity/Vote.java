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

    /**
     * Returns the vote id
     * @return  the vote id
     */
    public int getVoteId() {
        return voteId;
    }

    /**
     * Sets an id for vote
     * @param voteId    an id
     */
    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    /**
     * Returns the poll id
     * @return  the poll id
     */
    public int getPollId() {
        return pollId;
    }

    /**
     * Sets an id for poll
     * @param pollId
     */
    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    /**
     * Returns the voter id
     * @return  the voter id
     */
    public int getVoterId() {
        return voterId;
    }

    /**
     * Sets an id for voter
     * @param voterId   an id
     */
    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    /**
     * Returns the vote ranking list
     * @return  the vote ranking list
     */
    public LinkedHashMap<Integer, Integer> getVoteRankings() {
        return voteRankings;
    }

    /**
     * Sets a current vote ranking list
     * @param voteRankings  a vote ranking list
     */
    public void setVoteRankings(LinkedHashMap<Integer, Integer> voteRankings) {
        this.voteRankings = voteRankings;
    }

    /**
     * Returns the current ranking list
     * @return  the current ranking list
     */
    public LinkedHashMap<Integer, Integer> getCurrentRankings() {
        return currentRankings;
    }

    /**
     * Sets a current ranking list
     * @param currentRankings   a ranking list
     */
    public void setCurrentRankings(LinkedHashMap<Integer, Integer> currentRankings) {
        this.currentRankings = currentRankings;
    }
}
