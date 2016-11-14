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

    /**
     * Returns poll's id
     *
     * @return  the id of the poll
     */
    public int getPollid() {
        return pollid;
    }

    /**
     * Sets the id of the poll
     *
     * @param pollid    the poll id
     */
    public void setPollid(int pollid) {
        this.pollid = pollid;
    }

    /**
     * Returns poll title
     *
     * @return  the title of the poll
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the poll
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns poll description
     *
     * @return  the description of the poll
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the poll
     *
     * @param description   the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a boolean
     *
     * @return  a boolean
     */
    public Boolean getAvailable() {
        return available;
    }

    /**
     * Sets a boolean
     *
     * @param available a boolean
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /**
     * Returns poll creator
     *
     * @return  the creator of the poll
     */
    public int getCreator() {
        return creator;
    }

    /**
     * Sets the creator of the poll
     *
     * @param creator   the creator
     */
    public void setCreator(int creator) {
        this.creator = creator;
    }

    /**
     * Returns a list of choices
     *
     * @return  the choices in poll
     */
    public ArrayList<Choice> getChoices() {
        return choices;
    }

    /**
     * Sets the list of choices in the poll
     *
     * @param choices   the list of choices
     */
    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }

    /**
     * Returns a list of votes
     *
     * @return  the votes in poll
     */
    public ArrayList<Vote> getVotes() { return votes; }

    /**
     * Sets a list of votes in the poll
     *
     * @param votes the list of votes
     */
    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }

    /**
     * Returns a total count of votes
     *
     * @return  total count
     */
    public HashMap<Integer, Integer> getVoteCounts() {
        return voteCounts;
    }

    /**
     * Sets total counts of vote
     *
     * @param voteCounts    the total counts of vote
     */
    public void setVoteCounts(HashMap<Integer, Integer> voteCounts) {
        this.voteCounts = voteCounts;
    }

    /**
     * Returns a winner
     *
     * @return  the winner of the poll
     */
    public int getWinner() {
        return winner;
    }

    /**
     * Sets the winner of the poll
     *
     * @param winner    winner of poll
     */
    private  void setWinner(int winner) {
        this.winner = winner;
    }

    /**
     * Returns a poll status
     *
     * @return  the status of the poll
     */
    @Enumerated(EnumType.ORDINAL)
    public PollStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the poll
     *
     * @param status    the poll status
     */
    public void setStatus(PollStatus status) {
        this.status = status;
    }

    /**
     * Sets the code of the poll
     *
     * @param pollCode the code of the poll
     */
    public void setPollCode(String pollCode) { this.pollCode = pollCode; }

    /**
     * Returns a poll code
     *
     * @return  the code of the poll
     */
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

        //  TODO: Modify codes to remove multiple choices with the same amount of votes and are the lowest.

        for (Vote vote : votes) {
            vote.setCurrentRankings(vote.getVoteRankings());
        }

        while (winner == -1) {
            countVotes();
            if (!winnerExists()) {
                int choiceToRemove = getLowestVoteGetter();
                votes = removeChoiceFromContention(choiceToRemove, votes);
            } else {
                break;
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
    public void closePoll() { this.status = PollStatus.CLOSED; }
    /**
     *  Completes Poll and determines the winner
     */
    public void completePoll() {

        this.status = PollStatus.COMPLETED;
        determineWinner();
    }

    /**
     *  Finalizes Winner
     *
     *  @param  winner  the winner's id
     *  @param  status  the poll status
     */
    public void finalizeWinner(PollStatus status, int winner) {

        int winnerHolder = -1;

        if (status == PollStatus.COMPLETED &&  status == getStatus()) {

            winnerHolder = winner;
        }

        setWinner(winnerHolder);
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
        int index = 0;
        List<Integer> idsToReturn = null;

        int lowestVoteCount = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : voteCounts.entrySet()) {

            if (entry.getValue() < lowestVoteCount) {
                lowestVoteCount = entry.getValue();
                idToReturn = entry.getKey();
            }
            index++;
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
