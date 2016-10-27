package com.irvingmichael.irvapi.entity;

import org.apache.log4j.Logger;

import java.util.*;

import org.apache.commons.lang.RandomStringUtils;

import javax.persistence.*;


/**
 * Created by aaron on 9/10/16.
 */

@Entity
@Table(name = "Polls")
public class Poll {

    @Id
    @GeneratedValue
    @Column(name = "pollid")
    private int pollid;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="available")
    private Boolean available;

    @Transient
    private ArrayList<Choice> choices;
    @Transient
    private ArrayList<Vote> votes;
    @Transient
    private HashMap<Integer, Integer> voteCounts;

    @Column(name="creator")
    private int creator;

    @Column(name="winner")
    private int winner;

    @Column(name="pollcode")
    private String pollCode;

    private PollStatus status;

    public Poll() {}

    public Poll(String title) {
        this();
        this.title = title;
        winner = -1;
        choices = new ArrayList<Choice>();
        votes = new ArrayList<Vote>();
        voteCounts = new HashMap<Integer, Integer>();
        pollCode = "";
        status = PollStatus.INITIAL;
    }

    public int getPollid() {
        return pollid;
    }

    public void setPollid(int pollid) {
        this.pollid = pollid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }

    public HashMap<Integer, Integer> getVoteCounts() {
        return voteCounts;
    }

    public void setVoteCounts(HashMap<Integer, Integer> voteCounts) {
        this.voteCounts = voteCounts;
    }

    public int getWinner() {
        return winner;
    }

    private  void setWinner(int winner) {
        this.winner = winner;
    }

    @Enumerated(EnumType.STRING)
    public PollStatus getStatus() {
        return status;
    }

    public void setStatus(PollStatus status) {
        this.status = status;
    }

    String getPollCode() {
        if (pollCode.length() != 8) {
            pollCode = RandomStringUtils.random(8, true, true);
        }
        return pollCode;
    }

    int getWinThreshold() {
        int winThreshold = -1;
        if (votes.size() < 1) {
            winThreshold = -1;
        } else if (votes.size() % 2 == 0) {
            winThreshold = votes.size() / 2;
        } else {
            winThreshold = (votes.size() - 1) / 2;
        }
        return winThreshold;
    }

    void determineWinner() {

        status = PollStatus.CLOSED;

        for (Vote vote : votes) {
            vote.setCurrentRankings(vote.getVoteRankings());
        }

        while (winner == -1) {
            resetChoiceCounts();
            countVotes();
            if (!winnerExists()) {
                int choiceToRemove = getLowestVoteGetter();
                votes = removeChoiceFromContention(choiceToRemove, votes);
            }
        }
    }

    void countVotes() {
        setVotesCountsToZero();
        for (Vote vote : votes) {
            int currentChoice = findHighestRankedChoice(vote);
            voteCounts.put(currentChoice, voteCounts.get(currentChoice) + 1);
        }
    }



    void resetChoiceCounts() {
        voteCounts = new HashMap<Integer, Integer>();
        for (Choice choice : choices) {
            voteCounts.put(choice.getId(), 0);
        }
    }

    int findHighestRankedChoice(Vote vote) {
        int idToReturn = -1;
        int highestRank = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : vote.getCurrentRankings().entrySet()) {
            if (entry.getValue() < highestRank) {
                highestRank = entry.getValue();
                idToReturn = entry.getKey();
            }
        }

        return idToReturn;
    }

    Boolean winnerExists() {
        for (Map.Entry<Integer, Integer> entry : voteCounts.entrySet()) {
            if (entry.getValue() > getWinThreshold()) {
                this.winner = entry.getKey();
                return true;
            }
        }
        return false;
    }

    int getLowestVoteGetter() {
        int idToReturn = -1;
        int lowestVoteCount = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : voteCounts.entrySet()) {
            if (entry.getValue() < lowestVoteCount) {
                lowestVoteCount = entry.getValue();
                idToReturn = entry.getKey();
            }
        }
        return idToReturn;
    }

    ArrayList<Vote> removeChoiceFromContention(int idToRemove, ArrayList<Vote> votes) {
        ArrayList<Vote> newVotes = new ArrayList<Vote>();
        for (Vote vote : votes) {
            newVotes.add(removeChoiceFromVote(idToRemove, vote));
        }
        return newVotes;
    }

    Vote removeChoiceFromVote(int idToRemove, Vote vote) {
        Vote newVote = new Vote();
        newVote.setVoteRankings(vote.getVoteRankings());
        for (Map.Entry<Integer, Integer> entry : vote.getCurrentRankings().entrySet()) {
            if (entry.getKey() != idToRemove) {
                newVote.getCurrentRankings().put(entry.getKey(), entry.getValue());
            }
        }
        return newVote;
    }

    void setVotesCountsToZero() {
        voteCounts = new HashMap<Integer, Integer>();
        for (Choice choice : choices) {
            voteCounts.put(choice.getId(), 0);
        }
    }

    String getChoiceNameById(int id) {
        for (Choice choice : choices) {
            if (choice.getId() == id) {
                return choice.getName();
            }
        }
        return "Bad ID supplied";
    }

}
