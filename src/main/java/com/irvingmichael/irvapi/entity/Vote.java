package com.irvingmichael.irv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.LinkedHashMap;

/**
 * Created by aaron on 9/10/16.
 */

@Entity
@Table(name = "Votes")
public class Vote {

    @Id
    @Column(name = "voteid")

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
