package com.irvingmichael.irvapi.persistance;

import com.irvingmichael.irvapi.entity.Vote;
import org.hibernate.Session;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Aaron Anderson
 */
public class VoteDao extends GenericDao {

    private final Logger logger = Logger.getLogger(this.getClass());

    public VoteDao() {
        super(Vote.class);
    }

    public Vote getVoteByVoterIdPollId(int voterId, int pollId) {

        logger.debug("***** IN: getVoteByVoterIdPollId *****");
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Vote vote = new Vote();
        LinkedHashMap<Integer, Integer> tempMap = new LinkedHashMap<Integer, Integer>();

        String sqlQuery = "SELECT choiceid, rank FROM Votes WHERE voterid = " + voterId + " AND pollid = " + pollId + " ORDER BY choiceid";

        List<Object[]> rows = session.createSQLQuery(sqlQuery).list();

        for (Object[] row : rows) {
            int choiceId = (Integer) row[0];
            int rank = (Integer) row[1];
            tempMap.put(choiceId, rank);
        }

        vote.setCurrentRankings(tempMap);
        vote.setVoteRankings(tempMap);

        return vote;
    }
}
