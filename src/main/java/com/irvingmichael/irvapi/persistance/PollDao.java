package com.irvingmichael.irvapi.persistance;

import com.irvingmichael.irvapi.entity.Poll;
import com.irvingmichael.irvapi.entity.PollStatus;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Objects;

import static com.irvingmichael.irvapi.entity.PollStatus.COMPLETED;

/**
 * Created by Aaron Anderson on 10/9/16.
 */
public class PollDao extends GenericDao {

    private final Logger log = Logger.getLogger("debugLogger");

    /**
     * Empty constructor
     */
    public PollDao() {
        super(Poll.class);
    }

    /**
     * Gets all the polls for a specific voter
     * @param voterId Id of the voter to retrieve polls for
     * @return List of polls for the specified voter
     */
    public List<Poll> getAllPollsByVoterId(int voterId) {
        List<Poll> polls;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        polls = session.createCriteria(Poll.class)
                .add(Restrictions.eq("creator", voterId))
                .addOrder(Order.desc("pollid"))
                .list();
        session.close();
        log.debug(polls.size());
        return polls;
    }

    /**
     * Retrieve all polls for a specific email
     * @param email Email to retrieve polls for
     * @return List of polls for the specified email
     */
    public List<Poll> getAllPollsByEmail(String email) {
        VoterDao voterDao = new VoterDao();
        return getAllPollsByVoterId(voterDao.getVoterByEmail(email).getVoterId());
    }

    /**
     * Authorize a voter to have access to a poll via a pollcode
     * @param pollcode 8 digit code identifying poll to register voter for
     * @param voterId Voter id of voter to register with poll
     * @return True if it succeeds
     */
    public Boolean registerVoterForPoll(String pollcode, int voterId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        List<Poll> polls  = session.createCriteria(Poll.class)
                .add(Restrictions.eq("pollCode", pollcode))
                .list();
        if (polls.size() > 0) {
            Poll poll  = polls.get(0);
            Transaction tx = session.beginTransaction();
            SQLQuery sql = session.createSQLQuery("INSERT INTO VotersPolls (`voterid`, `pollid`) VALUES (:voter, :poll)");
            sql.setParameter("voter", voterId);
            sql.setParameter("poll", poll.getPollid());
            sql.executeUpdate();
            tx.commit();
            return true;
        } else {
            return false;
        }
    }
}
