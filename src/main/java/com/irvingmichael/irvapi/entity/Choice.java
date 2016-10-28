package com.irvingmichael.irvapi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Choice class to store information about a choice for a poll.
 *
 * @author Aaron Anderson
 */

@Entity
@Table(name = "Choices")
public class Choice {

    @Id
    @GeneratedValue
    @Column(name = "choiceid")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "pollid")
    private int pollId;

    /**
     * Empty constructor
     */
    protected Choice() {}

    /**
     * Constructor for Choice class
     * @param name Name of choice
     */
    public Choice(String name) { this(); this.setName(name); }

    /**
     * Constructor for Choice class
     * @param name Name of choice
     * @param pollId Id for poll that the choice is used in
     */
    public Choice(String name, int pollId) { this(name); this.setPollId(pollId); }

    /**
     * Constructor for Choice class
     * @param name Name of choice
     * @param pollId Id for poll that the choice is used in
     * @param description Long description of choice
     */
    public Choice(String name, int pollId, String description) { this(name, pollId); this.setDescription(description);}

    public void setId(int id) { this.id = id; }

    public int getId() {
        return id;
    }

    public void setName(String name) { this.name = name; }

    public String getName() {
        return name;
    }

    public void setDescription(String description) { this.description = description; }

    public String getDescription() { return description; }

    public int getPollId() { return pollId; }

    public void setPollId(int pollId) { this.pollId = pollId; }

}
