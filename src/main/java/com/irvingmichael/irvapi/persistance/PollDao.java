package com.irvingmichael.irvapi.persistance;

import com.irvingmichael.irvapi.entity.Poll;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Aaron Anderson on 10/9/16.
 */
public class PollDao extends GenericDao {

    private final Logger log = Logger.getLogger("debugLogger");

    public PollDao() {
        super(Poll.class);
    }

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

    public List<Poll> getAllPollsByEmail(String email) {
        VoterDao voterDao = new VoterDao();
        return getAllPollsByVoterId(voterDao.getVoterByEmail(email).getVoterId());
    }
}
