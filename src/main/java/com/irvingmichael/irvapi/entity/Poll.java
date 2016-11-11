package com.irvingmichael.irvapi.entity;

import com.irvingmichael.irvapi.persistance.PollDao;
import org.apache.log4j.Logger;

import java.util.*;

import org.apache.commons.lang.RandomStringUtils;

import javax.persistence.*;


/**
 * Poll class that holds the information for a specific poll and performs most of the functionality of a poll
 *
 * @author Aaron Anderson
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

    /**
     * Empty constructor, build code for poll. Code can be replaced for existing polls.
     */
    public Poll() { this.pollCode = RandomStringUtils.random(8, true, true); status = PollStatus.INITIAL; }

    /**
     * Main use constructor for a Poll
     * @param title Name of the poll
     */
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


    @Enumerated(EnumType.ORDINAL)
    public PollStatus getStatus() {
        return status;
    }

    public void setStatus(PollStatus status) {
        this.status = status;
    }

    public void setPollCode(String pollCode) { this.pollCode = pollCode; }

    public String getPollCode() {
        return pollCode;
    }

    /**
     * Determines the win threshold based on the number of choice in the poll
     * @return Number of votes required to win the poll
     */
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

    /**
     * Calculates and sets the winner of the poll
     */
    void determineWinner() {

        status = PollStatus.COMPLETED;

        for (Vote vote : votes) {
            vote.setCurrentRankings(vote.getVoteRankings());
        }

        while (winner == -1) {
            //setVotesCountsToZero();
            countVotes();
            if (!winnerExists()) {
                int choiceToRemove = getLowestVoteGetter();
                votes = removeChoiceFromContention(choiceToRemove, votes);
            }
        }
    }
    /**
     *  Opens Poll
     */
    public void openPoll() {
        this.status = PollStatus.OPEN;
    }
    /**
     *  Closes Poll
     */
    public void closePoll() {

        this.status = PollStatus.CLOSED;
    }

    /**
     * Initiates the counting of votes for the poll
     */
    void countVotes() {
        setVotesCountsToZero();
        for (Vote vote : votes) {
            int currentChoice = findHighestRankedChoice(vote);
            voteCounts.put(currentChoice, voteCounts.get(currentChoice) + 1);
        }
    }

    /**
     * Determines the highest ranked choice for the vote
     * @param vote Vote to find choice for
     * @return ChoiceId associated with the highest randed choice for vote
     */
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

    /**
     * Determines if one choice has enough votes to win
     * @return True if a choice has enough votes to win
     */
    Boolean winnerExists() {
        for (Map.Entry<Integer, Integer> entry : voteCounts.entrySet()) {
            if (entry.getValue() > getWinThreshold()) {
                this.winner = entry.getKey();
                return true;
            }
        }
        return false;
    }

    /**
     * Determines the choice that currently has the lowest number of votes
     * @return ChoiceId of lowest vote getting choice
     */
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

    /**
     * Removes choice from contention in current poll
     * @param idToRemove ChoiceId to remove
     * @param votes Array of votes to remove the choice form
     * @return Array of votes with choice removed
     */
    ArrayList<Vote> removeChoiceFromContention(int idToRemove, ArrayList<Vote> votes) {
        ArrayList<Vote> newVotes = new ArrayList<Vote>();
        for (Vote vote : votes) {
            newVotes.add(removeChoiceFromVote(idToRemove, vote));
        }
        return newVotes;
    }

    /**
     * Removes a specific choice from the supplied vote
     * @param idToRemove ChoiceId to remove
     * @param vote Vote to remove choice from
     * @return Vote without choice in current rankings
     */
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

    /**
     * Reset the vote counts for the current poll to zero
     */
    void setVotesCountsToZero() {
        voteCounts = new HashMap<Integer, Integer>();
        for (Choice choice : choices) {
            voteCounts.put(choice.getId(), 0);
        }
    }

    /**
     * Determines the choice name based on choice id
     * @param id ChoiceId to find name for
     * @return Name of Choice
     */
    String getChoiceNameById(int id) {
        for (Choice choice : choices) {
            if (choice.getId() == id) {
                return choice.getName();
            }
        }
        return "Bad ID supplied";
    }



}
