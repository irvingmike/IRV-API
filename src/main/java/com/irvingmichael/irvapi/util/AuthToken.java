package com.irvingmichael.irvapi.util;

import com.irvingmichael.irvapi.persistance.SessionFactoryProvider;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Aaron Anderson on 10/27/16.
 */
public final class AuthToken {

    // How long AuthToken should be valid in minutes
    private final static int TIME_TO_LIVE = 60;
    private static Session session = SessionFactoryProvider.getSessionFactory().openSession();

    /**
     * Generates a new authtoken and stores it in the database
     * @return Generated AuthToken
     */
    public static String getNewToken() {

        String newToken = RandomStringUtils.random(64, true, true);

        Transaction tx = session.beginTransaction();
        SQLQuery sql = session.createSQLQuery("INSERT INTO AuthTokens (token) VALUES (:token)");
        sql.setString("token", newToken);
        sql.executeUpdate();
        tx.commit();

        return newToken;
    }

    /**
     * Removes all expired tokens from the database, then queries the database for suppllied token
     * @param token AuthToken to validate in the database
     * @return True if AuthToken is in the database
     */
    public static boolean valid(String token) {
        removeExpiredTokens();
        SQLQuery query = session.createSQLQuery("SELECT TokenId FROM AuthTokens WHERE Token=:token");
        query.setParameter("token", token);
        List list = query.list();
        if (list.size() >0) {
            Transaction tx = session.beginTransaction();
            query = session.createSQLQuery("UPDATE AuthTokens SET Created = NOW() WHERE Token=:token");
            query.setString("token", token);
            query.executeUpdate();
            tx.commit();
            return true;
        }
        return false;
    }

    /**
     * Removes all tokens older the TIME_TO_LIVE
     */
    static void removeExpiredTokens() {
        Transaction tx = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM AuthTokens WHERE Created < ADDDATE(NOW(), INTERVAL -:ttl MINUTE)");
        query.setParameter("ttl", TIME_TO_LIVE);
        query.executeUpdate();
        tx.commit();
    }
}
