package com.irvingmichael.irv.persistance;

import com.irvingmichael.irv.entity.Choice;
import com.irvingmichael.irv.entity.Voter;
import com.irvingmichael.irv.persistance.SessionFactoryProvider;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron Anderson on 10/4/16.
 */
public class VoterDao<T> extends GenericDao {

    private Session session = SessionFactoryProvider.getSessionFactory().openSession();
    private final Logger log = Logger.getLogger(this.getClass());

    public VoterDao() { super(Voter.class); }

    public List<Voter> getAllVotersForPoll(int pollId) {
        List<Voter> voters;
        voters = session.createSQLQuery("SELECT Voters.voterid, Voters.firstname, Voters.lastname, Voters.email, Voters.securedby FROM Voters JOIN VotersPolls ON Voters.voterid = VotersPolls.voterid WHERE VotersPolls.pollid = 1").addEntity(Voter.class).list();
        return voters;
    }

    public void setPasswordInDB(int id, String password) {
        Transaction tx = session.beginTransaction();
        SQLQuery sql = session.createSQLQuery("UPDATE Voters SET securedby=:pass WHERE voterid=:id");
        sql.setString("pass", password);
        sql.setParameter("id", id);
        sql.executeUpdate();
        tx.commit();
    }
}
