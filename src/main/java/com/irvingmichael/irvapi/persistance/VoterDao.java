package com.irvingmichael.irvapi.persistance;

import com.irvingmichael.irvapi.entity.Voter;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Objects;

/**
 * Created by Aaron Anderson on 10/4/16.
 */
public class VoterDao<T> extends GenericDao {

    private Session session = SessionFactoryProvider.getSessionFactory().openSession();

    /**
     * Empty constructor
     */
    public VoterDao() { super(Voter.class); }

    /**
     * Retrieve a list of voter for a specified poll
     * @param pollId Poll id to get all the voter for
     * @return List of voter for the poll
     */
    public List<Voter> getAllVotersForPoll(int pollId) {
        List<Voter> voters;
        voters = session.createSQLQuery("SELECT Voters.voterid, Voters.firstname, Voters.lastname, Voters.email, Voters.securedby FROM Voters JOIN VotersPolls ON Voters.voterid = VotersPolls.voterid WHERE VotersPolls.pollid = " + pollId).addEntity(Voter.class).list();
        return voters;
    }

    /**
     * Set the password for the specified voter in the Voters database
     * @param id Id of the voter to set the password for
     * @param password Hashed password to store in database
     */
    public void setPasswordInDB(int id, String password) {
        Transaction tx = session.beginTransaction();
        SQLQuery sql = session.createSQLQuery("UPDATE Voters SET securedby=:pass WHERE voterid=:id");
        sql.setString("pass", password);
        sql.setParameter("id", id);
        sql.executeUpdate();
        tx.commit();
    }

    /**
     * Get the voter associated with specified email
     * @param email Email to get voter for
     * @return Voter with specified email
     */
    public Voter getVoterByEmail(String email) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        return (Voter) session.createCriteria(Voter.class)
                .add(Restrictions.eq("email", email))
                .list()
                .get(0);
    }

    /**
     * Verifies that valid voter credentials were supplied
     * @param email Email of voter to validate
     * @param password Password of voter to validate
     * @return True if the voter credentials are valid
     */
    public Boolean verifyUser(String email, String password) {
        Transaction tx = session.beginTransaction();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        SQLQuery sql = session.createSQLQuery("SELECT voterId FROM Voters WHERE email=:email AND securedby=:pass");
        sql.setString("email", email);
        sql.setString("pass", password);
        Object valid = sql.uniqueResult();
        tx.commit();

        return Objects.nonNull(valid);
    }

    /**
     * Validate a voter is in the database
     * @param voterId Voter id find in database
     * @return True if voter exists
     */
    public Boolean validateVoterId(int voterId) {
        Transaction tx = session.beginTransaction();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        SQLQuery sql = session.createSQLQuery("SELECT * FROM Voters WHERE voterId=:id");
        sql.setParameter("id", voterId);
        Object valid = sql.uniqueResult();
        tx.commit();
        return Objects.nonNull(valid);
    }
}
