package com.irvingmichael.irv.entity;

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
/*
    @Column(name = "securedby")
    private String password;
*/
    public  Voter() { this.voterId = -1; };

    public Voter(String email) {
        this();
        this.email = email;
        // this.password = password;
    }

    public Voter(String firstName, String lastName, String email) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setVoterId(int voterId) {
        if (this.voterId == -1) {
            this.voterId = voterId;
        }
    }
    public int getVoterId() {
        return voterId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
}
