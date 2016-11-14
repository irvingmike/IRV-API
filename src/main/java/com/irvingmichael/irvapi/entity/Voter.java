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
     *
     */
    public  Voter() { this.voterId = -1; };

    /**
     *
     * @param email
     */
    public Voter(String email) {
        this();
        this.email = email;
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param email
     */
    public Voter(String firstName, String lastName, String email) {
        this(email);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     *
     * @param voterId
     */
    public void setVoterId(int voterId) {
        if (this.voterId == -1) {
            this.voterId = voterId;
        }
    }

    /**
     *
     * @return
     */
    public int getVoterId() {
        return voterId;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }
}
