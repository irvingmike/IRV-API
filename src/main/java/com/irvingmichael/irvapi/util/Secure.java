package com.irvingmichael.irvapi.util;

import org.apache.catalina.realm.RealmBase;

/**
 * Created by Aaron Anderson on 10/28/16.
 */
public final class Secure {

    /**
     * Standardizes hashing of strings
     * @param stringToHash Sting to hash
     * @return Hashed string
     */
    public static String hash(String stringToHash) {
        return RealmBase.Digest(stringToHash, "Sha-256", "UTF-8");
    }

}
