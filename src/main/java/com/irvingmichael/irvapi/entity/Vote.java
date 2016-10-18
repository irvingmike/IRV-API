package com.irvingmichael.irvapi.entity;

import javax.persistence.*;
import java.util.LinkedHashMap;

/**
 * Created by aaron on 9/10/16.
 */

@Entity
@Table(name = "Votes")
public class Vote {

    @Id
    @GeneratedValue
    @Column(name = "voteid")
    int voteid;

    private LinkedHashMap<Integer, Integer> voteRankings;
    private LinkedHashMap<Integer, Integer> currentRankings;

    public Vote() {
        voteRankings = new LinkedHashMap<Integer, Integer>();
        currentRankings = new LinkedHashMap<Integer, Integer>();
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
