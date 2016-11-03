package com.irvingmichael.irvapi.persistance;

import com.irvingmichael.irvapi.entity.*;
import com.irvingmichael.irvapi.persistance.*;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aaron Anderson
 */
public class VoteDao extends GenericDao {

    private final Logger log = Logger.getLogger("debugLogger");
    Session session = SessionFactoryProvider.getSessionFactory().openSession();

    public VoteDao() {
        super(Vote.class);
    }

    public Vote getVoteByVoterIdPollId(int voterId, int pollId) {
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

    public Boolean recordRankingsInDatabase(Vote vote) {
        Boolean success = false;
        try {
            for (Map.Entry<Integer, Integer> entry : vote.getVoteRankings().entrySet()) {
                Transaction tx = session.beginTransaction();
                SQLQuery sql = session.createSQLQuery("INSERT INTO Votes (`voterid`, `pollid`, `choiceid`, `rank`) VALUES (:voter, :poll, :choice, :rank)");
                sql.setParameter("voter", vote.getVoterId());
                sql.setParameter("poll", vote.getPollId());
                sql.setParameter("choice", entry.getKey());
                sql.setParameter("rank", entry.getValue());
                sql.executeUpdate();
                tx.commit();
                success = true;
            }
        } catch (Exception e) {
            log.error(e);
        }
        return success;
    }

}
