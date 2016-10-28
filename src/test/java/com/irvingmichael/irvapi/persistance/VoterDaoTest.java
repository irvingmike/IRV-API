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
    public void validateVoterId() throws Exception {
        VoterDao voterDao = new VoterDao();
        assertTrue(voterDao.validateVoterId(11));
        assertFalse(voterDao.validateVoterId(-99));
    }

    @Test
    public void verifyUser() throws Exception {
        VoterDao voterDao = new VoterDao();
        assertTrue(voterDao.verifyUser("fake1@fake.com", "1cf311c819be2d73ab4a3c8cb5327418c4f9e30cb17adb4c9330272fcc8e984c"));
        assertFalse(voterDao.verifyUser("bademail", "1cf311c819be2d73ab4a3c8cb5327418c4f9e30cb17adb4c9330272fcc8e984c"));
        assertFalse(voterDao.verifyUser("fake1@fake.com", "badpass"));
    }

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
