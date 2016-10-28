package com.irvingmichael.irvapi.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aaron Anderson on 10/27/16.
 */
public class AuthTokenTest {

    private String token;

    @Test
    public void getNewToken() throws Exception {
        token = AuthToken.getNewToken();
        assertTrue(token.length() == 64);
    }

    @Before
    public void setup() {
        token = AuthToken.getNewToken();
    }
    @Test
    public void valid() throws Exception {
        assertTrue(AuthToken.valid(token));
        assertTrue(AuthToken.valid("fTV0pTUhiAiAUTX3dVnKuJae3EuvYFrD7Fmtd1h9i1R7wxgEMfmmXtISzti6V0oL"));
    }

    @Test
    public void removeExpiredTokens() throws Exception {
        AuthToken.removeExpiredTokens();
    }

}