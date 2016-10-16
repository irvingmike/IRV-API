package com.irvingmichael.irv.persistance;

import com.irvingmichael.irv.entity.Voter;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aaron Anderson on 10/4/16.
 */
public class VoterDaoTest {

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
