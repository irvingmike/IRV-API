package com.irvingmichael.irvapi.util;

import java.security.MessageDigest;

/**
 * Created by Aaron Anderson on 10/14/16.
 */
public final class SecurityTools {

    public final static String hashString(String stringToHash) {
        String encryptedString = "Bad hash";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(stringToHash.getBytes());
            encryptedString = new String(messageDigest.digest());
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        };
        return encryptedString;
    }
}
