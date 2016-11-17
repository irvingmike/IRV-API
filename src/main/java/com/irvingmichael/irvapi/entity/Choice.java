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
     * This constructor is for testing only. Id should always be from database.
     * @param id    the Choice id
     * @param name  the Choice name
     */
    Choice(int id, String name) {this(name); this.setId(id); }

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

    /**
     * Sets id for Choice
     *
     * @param id    an id
     */
    public void setId(int id) { this.id = id; }

    /**
     * Returns Choice's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets Choice name
     *
     * @param name  a name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns Choice's name
     *
     * @return  the name of Choice
     */
    public String getName() {
        return name;
    }

    /**
     * Sets description of Choice
     *
     * @param description   a description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Return the description of Choice
     *
     * @return  the description of Choice
     */
    public String getDescription() { return description; }

    /**
     * Returns poll id
     *
     * @return  the poll id
     */
    public int getPollId() { return pollId; }

    /**
     * Sets an id for the poll
     *
     * @param pollId    an id
     */
    public void setPollId(int pollId) { this.pollId = pollId; }

}
