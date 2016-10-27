package com.irvingmichael.irvapi.persistance;

import com.irvingmichael.irvapi.entity.*;
import com.irvingmichael.irvapi.persistance.*;
import org.hibernate.Session;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Aaron Anderson
 */
public class VoteDao extends GenericDao {

    private final Logger log = Logger.getLogger("debugLogger");

    public VoteDao() {
        super(Vote.class);
    }

    public Vote getVoteByVoterIdPollId(int voterId, int pollId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Vote vote = new Vote(voterId, pollId);

        LinkedHashMap<Integer, Integer> tempMap = new LinkedHashMap<Integer, Integer>();

        String sqlQuery = "SELECT choiceid, rank FROM Votes WHERE voterid = " + voterId + " AND pollid = " + pollId + " ORDER BY choiceid";

        List<Object[]> rows = session.createSQLQuery(sqlQuery).list();

        for (Object[] row : rows) {
            int choiceId = (int) row[0];
            int rank = (int) row[1];
            tempMap.put(choiceId, rank);
        }

        vote.setCurrentRankings(tempMap);
        vote.setVoteRankings(tempMap);

        return vote;
    }

}
