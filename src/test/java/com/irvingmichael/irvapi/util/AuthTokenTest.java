package com.irvingmichael.irvapi.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aaron Anderson on 10/27/16.
 */
public class AuthTokenTest {

    @Test
    public void getNewToken() throws Exception {
        String token = AuthToken.getNewToken();
        assertTrue(token.length() == 64);
    }

    @Test
    public void valid() throws Exception {
        String token = AuthToken.getNewToken();
        assertTrue(AuthToken.valid(token));
    }

    @Test
    public void removeExpiredTokens() throws Exception {
        AuthToken.removeExpiredTokens();
    }

}