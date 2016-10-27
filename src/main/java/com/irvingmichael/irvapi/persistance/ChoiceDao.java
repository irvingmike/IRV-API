package com.irvingmichael.irvapi.persistance;

import com.irvingmichael.irvapi.entity.Choice;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Dao class for accessing the Choice information in database
 *
 * @author Aaron Anderson
 */
public class ChoiceDao extends GenericDao {

    private final Logger log = Logger.getLogger("debugLogger");

    public ChoiceDao() { super(Choice.class); }

    /**
     * Returns a list of all choices in the database
     * @return
     */
    List<Choice> getAllChoicesForPoll(int pollid) {
        List<Choice> choices;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        choices = session.createCriteria(Choice.class)
                .add(Restrictions.eq("pollId", pollid))
                .addOrder(Order.asc("name"))
                .list();
        session.close();
        return choices;
    }
}
