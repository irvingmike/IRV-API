package com.irvingmichael.irvapi.entity;

import javax.persistence.*;

/**
 * Class to hold information about a specific voter
 *
 * @author Aaron Anderson
 */

@Entity
@Table(name = "Voters")
public class Voter {

    @Id
    @GeneratedValue
    @Column(name = "voterid")
    private int voterId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    /**
     *  Empty constructor to initialize Voter id
     */
    public  Voter() { this.voterId = -1; };

    /**
     * Constructor to initialize Voter email
     *
     * @param email
     */
    public Voter(String email) {
        this();
        this.email = email;
    }

    /**
     * Main Constructor to initialize Voter attributes
     * @param firstName     first name of Voter
     * @param lastName      last name of Voter
     * @param email         Voter e-mail
     */
    public Voter(String firstName, String lastName, String email) {
        this(email);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Sets an id for Voter
     * @param voterId
     */
    public void setVoterId(int voterId) {
        if (this.voterId == -1) {
            this.voterId = voterId;
        }
    }

    /**
     * Returns the Voter id
     * @return  the id
     */
    public int getVoterId() {
        return voterId;
    }

    /**
     * Returns Voter's first name
     * @return  the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *  Sets a first name for Voter
     * @param firstName     a first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns Voter's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets a last name for Voter
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns Voter's e-mail
     * @return  Voter's e-mail
     */
    public String getEmail() {
        return email;
    }
}
