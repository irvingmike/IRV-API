package com.irvingmichael.irvapi.persistance;

import com.irvingmichael.irvapi.entity.Voter;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aaron Anderson on 10/4/16.
 */
public class VoterDaoTest {

    @Test
    public void getVoterByEmail() throws Exception {
        VoterDao voterDao = new VoterDao();
        String email = "irvingmichael@gmail.com";
        Voter voter = voterDao.getVoterByEmail(email);
        assertEquals("Bad voter returned by getVoterByEmail", 11, voter.getVoterId());
    }

    private final Logger log = Logger.getLogger(this.getClass());

    @Test
    public void setPasswordInDB() throws Exception {
        VoterDao voterDao = new VoterDao();
        voterDao.setPasswordInDB(2, "testpass");
    }

    @Test
    public void testGetAllVotersForPoll() throws Exception {
        VoterDao voterDao = new VoterDao();
        assertEquals("Incorrect number of voters retrieved", 10, voterDao.getAllVotersForPoll(1).size());
    }
}
