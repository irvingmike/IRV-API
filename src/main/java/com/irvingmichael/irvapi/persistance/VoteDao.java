package com.irvingmichael.irv.persistance;

import com.irvingmichael.irv.entity.Vote;
import com.irvingmichael.irv.entity.Voter;
import org.hibernate.Session;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Aaron Anderson on 10/9/16.
 */
public class VoteDao extends GenericDao {

    private final Logger logger = Logger.getLogger(this.getClass());

    public VoteDao() {
        super(Vote.class);
    }

    public Vote getVoteByVoterIdPollId(int voterId, int pollId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Vote vote = new Vote();
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
